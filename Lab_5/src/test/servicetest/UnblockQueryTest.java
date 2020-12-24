package test.servicetest;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.Test;

import classes.kpi.model.dao.DAOUnblockQuery;
import classes.kpi.model.services.UnblockQueryService;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UnblockQueryTest {
    DAOUnblockQuery daoUnblockQuery;
    @Before
    public void init(){
        daoUnblockQuery = mock(DAOUnblockQuery.class);
    }


    @Test
    public void getAllTest0(){
        UnblockQueryService unblockQueryService = new UnblockQueryService(daoUnblockQuery);

        ArrayList<Integer> arr =  new ArrayList<Integer>();

        when(unblockQueryService.getAll()).thenReturn(arr);

        ArrayList<Integer> query = unblockQueryService.getAll();
        assertEquals(arr, query);
        assertEquals(0, query.size());
        assertTrue(query.isEmpty());
        verify(daoUnblockQuery).getAll();

    }

    @Test
    public void getAllTest1(){
        UnblockQueryService unblockQueryService = new UnblockQueryService(daoUnblockQuery);

        ArrayList<Integer> arr =  new ArrayList<Integer>();
        for(int i = 0 ;i < 10;i++){
            arr.add(i);
        }
        when(daoUnblockQuery.getAll()).thenReturn(arr);

        ArrayList<Integer> query = unblockQueryService.getAll();
        assertEquals(arr, query);
        assertEquals(10, query.size());

        assertEquals(java.util.Optional.of(0), java.util.Optional.of(query.get(0)));

        verify(daoUnblockQuery).getAll();

    }

    @Test
    public void insertTest1(){
        UnblockQueryService unblockQueryService = new UnblockQueryService(daoUnblockQuery);

        doThrow(new RuntimeException()).when(daoUnblockQuery).insert(1);

        unblockQueryService.insert(2);

        verify(daoUnblockQuery).insert(anyInt());
    }



    @Test(expected =  RuntimeException.class)
    public void insertTest2(){
        UnblockQueryService unblockQueryService = new UnblockQueryService(daoUnblockQuery);

        doThrow(new RuntimeException()).when(daoUnblockQuery).insert(1);

        unblockQueryService.insert(1);

        verify(daoUnblockQuery).insert(anyInt());
    }

    @Test
    public void deleteTest1(){
        UnblockQueryService unblockQueryService = new UnblockQueryService(daoUnblockQuery);

        doThrow(new RuntimeException()).when(daoUnblockQuery).delete(1);

        unblockQueryService.delete(2);

        verify(daoUnblockQuery).delete(anyInt());
    }

    @Test(expected =  RuntimeException.class)
    public void deleteTest2(){
        UnblockQueryService unblockQueryService = new UnblockQueryService(daoUnblockQuery);

        doThrow(new RuntimeException()).when(daoUnblockQuery).delete(1);

        unblockQueryService.delete(1);

        verify(daoUnblockQuery).delete(anyInt());
    }
}
