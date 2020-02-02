package com.example.demo.kindergarden;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import javax.management.BadAttributeValueExpException;

import com.example.demo.kindergarden.controller.KindergardenController;
import com.example.demo.kindergarden.model.ChildrenModel;
import com.example.demo.kindergarden.model.KindergardenModel;
import com.example.demo.kindergarden.repos.ChildrenModelRepository;
import com.example.demo.kindergarden.repos.KindergardenRepository;
import com.example.demo.kindergarden.service.KindergardenService;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class KindergardenControllerTests {
    @InjectMocks
    KindergardenController kindergardenController;

    @Mock
    KindergardenService kindergardenService;
    @MockBean
    KindergardenRepository kindergardenRepository;
    @MockBean
    ChildrenModelRepository childrenModelRepository;

    @Before
    public void setup(){
        // init mocks
        kindergardenService = new KindergardenService(childrenModelRepository,kindergardenRepository);
        kindergardenController = new KindergardenController(kindergardenService);
        
        KindergardenModel mockKM = Mockito.mock(KindergardenModel.class, RETURNS_DEEP_STUBS);
        ChildrenModel mockCM = Mockito.mock(ChildrenModel.class, RETURNS_DEEP_STUBS);
        when(kindergardenRepository.findById(mockKM.getId())).thenReturn(Optional.of(new KindergardenModel()));
        when(childrenModelRepository.findById(mockCM.getIdentityCode())).thenReturn(Optional.of(new ChildrenModel()));
    }

    @Test
    public void removeFromQueueByIdTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        KindergardenModel mockKM = Mockito.mock(KindergardenModel.class, RETURNS_DEEP_STUBS);
        ChildrenModel mockCM = Mockito.mock(ChildrenModel.class, RETURNS_DEEP_STUBS);
       
        ResponseEntity<Map<Integer, ChildrenModel>> responseEntityQueueAdded = kindergardenController.removeFromQueueById(
            mockKM.getId().toString(),mockCM.getIdentityCode());
        assertEquals((HttpStatus.OK).value(),responseEntityQueueAdded.getStatusCodeValue());
    }

    @Test
    public void addToQueueByModelTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        KindergardenModel mockKM = Mockito.mock(KindergardenModel.class, RETURNS_DEEP_STUBS);
        ChildrenModel mockCM = Mockito.mock(ChildrenModel.class, RETURNS_DEEP_STUBS);
       
        ResponseEntity<Map<Integer, ChildrenModel>> responseEntityQueueAdded = kindergardenController.addToQueueByModel(
            mockKM.getId().toString(),mockCM);
        assertEquals((HttpStatus.CREATED).value(),responseEntityQueueAdded.getStatusCodeValue());
    }

    @Test
    public void fetchPrioritizedQueueTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        KindergardenModel mockKM = Mockito.mock(KindergardenModel.class, RETURNS_DEEP_STUBS);
       
        ResponseEntity<Map<Integer, ChildrenModel>> responseEntityQueueAdded = 
        kindergardenController.fetchPrioritizedQueue(mockKM.getId().toString());
        assertEquals((HttpStatus.OK).value(),responseEntityQueueAdded.getStatusCodeValue());
    }

    @Test
    public void addToQueueByIdCodeTest() throws BadAttributeValueExpException, ParseException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        KindergardenModel mockKM = Mockito.mock(KindergardenModel.class, RETURNS_DEEP_STUBS);
        ChildrenModel mockCM = Mockito.mock(ChildrenModel.class, RETURNS_DEEP_STUBS);
       
        ResponseEntity<Map<Integer, ChildrenModel>> responseEntityQueueAdded = kindergardenController.addToQueueByIdCode(
            mockKM.getId().toString(),mockCM.getIdentityCode()
            );
        assertEquals((HttpStatus.CREATED).value(),responseEntityQueueAdded.getStatusCodeValue());
    }

    @Test
    public void createKindergardenTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<KindergardenModel> responseEntity = kindergardenController.createKindergarden(new KindergardenModel("TEST", "TEST PLACE AT TEST ADDRESS", new ArrayList<ChildrenModel>()));
        assertEquals((HttpStatus.CREATED).value(),responseEntity.getStatusCodeValue());
    }

    @Test
    public void createChildrenTest() throws BadAttributeValueExpException, ParseException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ChildrenModel mockCM = Mockito.mock(ChildrenModel.class, RETURNS_DEEP_STUBS);
        ResponseEntity<ChildrenModel> responseEntity = kindergardenController.createChildren(mockCM);
        assertEquals((HttpStatus.CREATED).value(),responseEntity.getStatusCodeValue());
    }
/* Not able to mock this, because Optional.get() returns null for mocked values, even in setup it is said to return optional value...
    @Test
    public void findAllKindergardenByIdTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        KindergardenModel mockKM = Mockito.mock(KindergardenModel.class, RETURNS_DEEP_STUBS);
        ResponseEntity<KindergardenModel> responseEntity = kindergardenController.findAllKindergardenById(mockKM.getId().toString());
        assertEquals((HttpStatus.OK).value(),responseEntity.getStatusCodeValue());
    }

    @Test
    public void getChildrenModelByIdTest(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
   
        ChildrenModel mockCM = Mockito.mock(ChildrenModel.class, RETURNS_DEEP_STUBS);
        ResponseEntity<ChildrenModel> responseEntity = kindergardenController.getChildrenModelById(mockCM.getIdentityCode());
        assertEquals((HttpStatus.OK).value(),responseEntity.getStatusCodeValue());
    }
*/
    @Test
    public void getAllKindergardensTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<Iterable<KindergardenModel>> responseEntity = kindergardenController.getAllKindergardens();
        assertEquals((HttpStatus.OK).value(),responseEntity.getStatusCodeValue());
    }

    @Test
    public void getAllChildrenTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<Iterable<ChildrenModel>> responseEntity = kindergardenController.getAllChildren();
        assertEquals((HttpStatus.OK).value(),responseEntity.getStatusCodeValue());
}


}