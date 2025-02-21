package lt.ca.javau11.services;

import lt.ca.javau11.entities.User;
import lt.ca.javau11.entities.mappers.EntityMapper;
import lt.ca.javau11.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private EntityMapper entityMapper;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testUser");
    }

    @Test
    void testGetAllUsers() {
        when(userRepo.findAll()).thenReturn(List.of(user));
        List<User> result = userService.getAllUsers();
        assertEquals(1, result.size());
        assertEquals("testUser", result.get(0).getUsername());
    }

    @Test
    void testGetUserByID_Found() {
        when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> result = userService.getUserByID(1L);
        assertTrue(result.isPresent());
        assertEquals("testUser", result.get().getUsername());
    }

    @Test
    void testGetUserByID_NotFound() {
        when(userRepo.findById(1L)).thenReturn(Optional.empty());
        Optional<User> result = userService.getUserByID(1L);
        assertFalse(result.isPresent());
    }

    @Test
    void testAddUser() {
        when(userRepo.save(any(User.class))).thenReturn(user);
        User result = userService.addUser(user);
        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
    }

    @Test
    void testUpdateUser_Found() {
        User updatedUser = new User();
        updatedUser.setUsername("updatedUser");

        when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(entityMapper).updateUser(any(User.class), any(User.class));
        when(userRepo.save(any(User.class))).thenReturn(updatedUser);

        Optional<User> result = userService.updateUser(updatedUser, 1L);
        assertTrue(result.isPresent());
        assertEquals("updatedUser", result.get().getUsername());
    }

    @Test
    void testUpdateUser_NotFound() {
        User updatedUser = new User();
        updatedUser.setUsername("updatedUser");

        when(userRepo.findById(1L)).thenReturn(Optional.empty());

        Optional<User> result = userService.updateUser(updatedUser, 1L);
        assertFalse(result.isPresent());
    }

    @Test
    void testDeleteUser_Success() {
        when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        boolean result = userService.deleteUser(1L);
        assertTrue(result);
        verify(userRepo, times(1)).delete(user);
    }

    @Test
    void testDeleteUser_NotFound() {
        when(userRepo.findById(1L)).thenReturn(Optional.empty());
        boolean result = userService.deleteUser(1L);
        assertFalse(result);
        verify(userRepo, never()).delete(any());
    }
}