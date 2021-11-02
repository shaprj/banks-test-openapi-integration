package shaprj.mockito.sample1.service;

import java.util.List;
import java.util.UUID;

public interface DataStorage {

    UUID save(String data);

    List<UUID> saveAll(List<String> data);

    String findDataByUuid(UUID uuid);

    List<String> findAllDataByUuidList(List<UUID> uuidList);

}
