package shaprj.mockito.sample1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import shaprj.mockito.sample1.service.DataStorage;
import shaprj.mockito.sample1.service.MapDataStorage;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class DataStorageTest {

    @Test
    @DisplayName("testDataStorage1")
    public void testDataStorage1() {
        var dataStorage = mock(DataStorage.class);
        var uuid1 = dataStorage.save("Hello");
        var uuid2 = verify(dataStorage, times(1)).save("Hello");
        var data1 = dataStorage.findDataByUuid(uuid1);
        var allData = dataStorage.findAllDataByUuidList(Arrays.stream(new UUID[]{uuid1})
                .collect(Collectors.toList()));
    }

    @Test
    @DisplayName("testDataStorage2")
    public void testDataStorage2() {
        var dataList = Arrays.asList("Hello", "World", "!!!");
        var dataStorage = spy(MapDataStorage.class);
        var uuidList = dataList.stream().map(data -> dataStorage.save("Hello"))
                .collect(Collectors.toList());

        var data1 = dataStorage.findDataByUuid(uuidList.get(0));
        assertThat(data1.equals(dataList.get(0)));

        var allData = dataStorage.findAllDataByUuidList(uuidList);
        assertThat(allData.size() == uuidList.size());
        assertThat(allData.containsAll(dataList));
    }
}
