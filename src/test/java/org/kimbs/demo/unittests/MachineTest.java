package org.kimbs.demo.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kimbs.demo.service.Machine;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

@ExtendWith(MockitoExtension.class)
public class MachineTest {

    @Mock
    Machine machine;

    @Test
    void test1() {
        OngoingStubbing<Integer> i = Mockito.when(machine.getPrice("Test")).thenReturn(17000);
    }
}
