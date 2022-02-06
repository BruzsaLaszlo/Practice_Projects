package Human;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static Human.Gender.MALE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

//@ExtendWith(MockitoExtension.class)
class HumanServiceTest {

    @Mock
    HumanRepository humanRepository;
    AutoCloseable autoCloseable;
    HumanService humanService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        humanService = new HumanService(humanRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllHuman() {
        humanService.getHumans();
        verify(humanRepository).findAll();
    }

    @Test
    void getHumans() {
    }

    @Test
    void addAllHumans() {
    }

    @Test
    void getHumanRepository() {
    }

    @Test
    void deleteHuman() {
    }

    @Test
    void updateHuman() {
    }

    @Test
    void addHuman() {
        Human jancsi = new Human("Jancsi", 12, MALE);
        humanService.addHuman(jancsi);
        ArgumentCaptor<Human> humanArgumentCaptor = forClass(Human.class);
        verify(humanRepository).save(humanArgumentCaptor.capture());
        Human capturedHuman = humanArgumentCaptor.getValue();
        assertEquals(jancsi, capturedHuman);
    }

    @Test
    void addHumanAndThrowsException() {
        Human jancsi = new Human("Jancsi", 12, MALE);
        given(humanRepository.equalsHumanByNameAndAge(anyString(), anyInt())).willReturn(Optional.of(jancsi));
        assertThrows(IllegalStateException.class, () -> humanService.addHuman(jancsi));
        verify(humanRepository, never()).save(any());
    }
}