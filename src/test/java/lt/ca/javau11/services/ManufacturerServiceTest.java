package lt.ca.javau11.services;

import lt.ca.javau11.entities.Manufacturer;
import lt.ca.javau11.entities.mappers.EntityMapper;
import lt.ca.javau11.exceptions.NotFoundException;
import lt.ca.javau11.repositories.ManufacturerRepository;
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
class ManufacturerServiceTest {

    @Mock
    private ManufacturerRepository manufacturerRepo;

    @Mock
    private EntityMapper entityMapper;

    @InjectMocks
    private ManufacturerService manufacturerService;

    private Manufacturer manufacturer;

    @BeforeEach
    void setUp() {
        manufacturer = new Manufacturer();
        manufacturer.setId(1L);
        manufacturer.setName("Test Manufacturer");
    }

    @Test
    void testGetAllManufacturers() {
        when(manufacturerRepo.findAll()).thenReturn(List.of(manufacturer));
        List<Manufacturer> result = manufacturerService.getAllManufacturers();
        assertEquals(1, result.size());
        assertEquals("Test Manufacturer", result.get(0).getName());
    }

    @Test
    void testAddNewManufacturer() {
        when(manufacturerRepo.save(any(Manufacturer.class))).thenReturn(manufacturer);
        Manufacturer result = manufacturerService.addNewManufacturer(manufacturer);
        assertNotNull(result);
        assertEquals("Test Manufacturer", result.getName());
    }

    @Test
    void testGetManufacturerByID_Found() {
        when(manufacturerRepo.findById(1L)).thenReturn(Optional.of(manufacturer));
        Manufacturer result = manufacturerService.getManufacturerByID(1L);
        assertNotNull(result);
        assertEquals("Test Manufacturer", result.getName());
    }

    @Test
    void testGetManufacturerByID_NotFound() {
        when(manufacturerRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> manufacturerService.getManufacturerByID(1L));
    }

    @Test
    void testUpdateManufacturer_Success() {
        Manufacturer updatedManufacturer = new Manufacturer();
        updatedManufacturer.setName("Updated Manufacturer");

        when(manufacturerRepo.findById(1L)).thenReturn(Optional.of(manufacturer));
        when(manufacturerRepo.save(any(Manufacturer.class))).thenReturn(manufacturer);

        Optional<Manufacturer> result = manufacturerService.updateManufacturer(1L, updatedManufacturer);
        assertTrue(result.isPresent());
    }

    @Test
    void testUpdateManufacturer_NotFound() {
        Manufacturer updatedManufacturer = new Manufacturer();
        updatedManufacturer.setName("Updated Manufacturer");

        when(manufacturerRepo.findById(1L)).thenReturn(Optional.empty());
        Optional<Manufacturer> result = manufacturerService.updateManufacturer(1L, updatedManufacturer);
        assertFalse(result.isPresent());
    }

    @Test
    void testDeleteManufacturer_Success() {
        when(manufacturerRepo.findById(1L)).thenReturn(Optional.of(manufacturer));
        boolean result = manufacturerService.deleteManufacturer(1L);
        assertTrue(result);
        verify(manufacturerRepo, times(1)).delete(manufacturer);
    }

    @Test
    void testDeleteManufacturer_NotFound() {
        when(manufacturerRepo.findById(1L)).thenReturn(Optional.empty());
        boolean result = manufacturerService.deleteManufacturer(1L);
        assertFalse(result);
        verify(manufacturerRepo, never()).delete(any());
    }
}
