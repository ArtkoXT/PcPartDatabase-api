package lt.ca.javau11.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lt.ca.javau11.entities.Category;
import lt.ca.javau11.entities.Component;
import lt.ca.javau11.entities.DTOs.ComponentDto;
import lt.ca.javau11.entities.Manufacturer;
import lt.ca.javau11.exceptions.NotFoundException;
import lt.ca.javau11.repositories.ComponentRepository;
import lt.ca.javau11.repositories.ManufacturerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComponentServiceTest {

    @Mock
    private ManufacturerRepository manufacturerRepo;

    @Mock
    private ComponentRepository componentRepository;

    @InjectMocks
    private ComponentService componentService;

    private Component component;
    private ComponentDto componentDto;
    private Manufacturer manufacturer;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        manufacturer = new Manufacturer();
        manufacturer.setId(1L);
        manufacturer.setName("Test Manufacturer");

        component = new Component();
        component.setId(1L);
        component.setName("Test Component");
        component.setManufacturer(manufacturer);
        component.setCategory(Category.CPU);
        component.setPrice(199.99);
        component.setProperties(objectMapper.createObjectNode());

        componentDto = new ComponentDto();
        componentDto.setId(1L);
        componentDto.setManufacturer_id(1L);
        componentDto.setName("Test Component");
        componentDto.setCategory(Category.CPU);
        componentDto.setPrice(199.99);
        componentDto.setProperties(objectMapper.createObjectNode());
    }

    @Test
    void testListAllComponents() {
        when(componentRepository.findAll()).thenReturn(List.of(component));
        List<ComponentDto> result = componentService.listAllComponents();
        assertEquals(1, result.size());
        assertEquals("Test Component", result.get(0).getName());
    }

    @Test
    void testGetComponentById_Found() {
        when(componentRepository.findById(1L)).thenReturn(Optional.of(component));
        Optional<ComponentDto> result = componentService.getComponentByID(1L);
        assertTrue(result.isPresent());
        assertEquals("Test Component", result.get().getName());
    }

    @Test
    void testGetComponentById_NotFound() {
        when(componentRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<ComponentDto> result = componentService.getComponentByID(1L);
        assertFalse(result.isPresent());
    }

    @Test
    void testDeleteComponent_Success() {
        when(componentRepository.findById(1L)).thenReturn(Optional.of(component));
        boolean result = componentService.deleteComponent(1L);
        assertTrue(result);
        verify(componentRepository, times(1)).delete(component);
    }

    @Test
    void testDeleteComponent_NotFound() {
        when(componentRepository.findById(1L)).thenReturn(Optional.empty());
        boolean result = componentService.deleteComponent(1L);
        assertFalse(result);
        verify(componentRepository, never()).delete(any());
    }

    @Test
    void testAddNewComponent_Success() {
        when(manufacturerRepo.findById(1L)).thenReturn(Optional.of(manufacturer));
        when(componentRepository.save(any(Component.class))).thenReturn(component);

        ComponentDto result = componentService.addNewComponent(componentDto);
        assertNotNull(result);
        assertEquals("Test Component", result.getName());
    }

    @Test
    void testAddNewComponent_ManufacturerNotFound() {
        when(manufacturerRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> componentService.addNewComponent(componentDto));
    }

    @Test
    void testUpdateComponent_Success() {
        when(componentRepository.findById(1L)).thenReturn(Optional.of(component));
        when(manufacturerRepo.findById(1L)).thenReturn(Optional.of(manufacturer));
        when(componentRepository.save(any(Component.class))).thenReturn(component);

        ComponentDto result = componentService.updateComponent(1L, componentDto);
        assertNotNull(result);
        assertEquals("Test Component", result.getName());
    }

    @Test
    void testUpdateComponent_NotFound() {
        when(componentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> componentService.updateComponent(1L, componentDto));
    }

    @Test
    void testUpdateComponent_ManufacturerNotFound() {
        when(componentRepository.findById(1L)).thenReturn(Optional.of(component));
        when(manufacturerRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> componentService.updateComponent(1L, componentDto));
    }
}
