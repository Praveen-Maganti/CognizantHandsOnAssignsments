package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MyServiceTest {

    // Exercise 1: Mocking and Stubbing
    @Test
    public void testExternalApi() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Mock Data");
        
        MyService service = new MyService(mockApi);
        String result = service.fetchData();
        
        assertEquals("Mock Data", result);
    }

    // Exercise 2: Verifying Interactions
    @Test
    public void testVerifyInteraction() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);
        
        service.fetchData();
        
        verify(mockApi).getData();
    }

    // Exercise 3: Argument Matching
    @Test
    public void testArgumentMatching() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);
        
        when(mockApi.getDataById(anyInt())).thenReturn("Data for ID");
        
        String result = service.fetchDataById(42);
        
        assertEquals("Data for ID", result);
        verify(mockApi).getDataById(eq(42));
    }

    // Exercise 4: Handling Void Methods
    @Test
    public void testVoidMethod() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);
        
        // Stubbing is optional for void methods if they just do nothing (default mock behavior),
        // but we can explicitly use doNothing()
        doNothing().when(mockApi).performAction();
        
        service.doAction();
        
        verify(mockApi, times(1)).performAction();
    }

    // Exercise 5: Mocking and Stubbing with Multiple Returns
    @Test
    public void testMultipleReturns() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);
        
        when(mockApi.getData())
            .thenReturn("First Call")
            .thenReturn("Second Call");
            
        assertEquals("First Call", service.fetchData());
        assertEquals("Second Call", service.fetchData());
    }

    // Exercise 6: Verifying Interaction Order
    @Test
    public void testInteractionOrder() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);
        
        service.processMultiple();
        
        // Verify that getData() was called before performAction()
        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).getData();
        inOrder.verify(mockApi).performAction();
    }

    // Exercise 7: Handling Void Methods with Exceptions
    @Test
    public void testVoidMethodWithException() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);
        
        doThrow(new RuntimeException("API Error")).when(mockApi).performActionWithException();
        
        assertThrows(RuntimeException.class, () -> {
            service.doActionWithException();
        });
        
        verify(mockApi).performActionWithException();
    }
}
