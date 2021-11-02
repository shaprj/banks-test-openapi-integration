package shaprj.mockito.sample1.service;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MapDataStorage implements DataStorage{

    private Map<UUID, String> map = new HashMap<>();

    private UUID putAndReturnUuid(String data){
        var newUuid = UUID.randomUUID();
        map.put(newUuid, data);
        return newUuid;
    }

    @Override
    public UUID save(String data) {
        return putAndReturnUuid(data);
    }

    @Override
    public List<UUID> saveAll(List<String> data) {
        return data.stream()
                .map(this::putAndReturnUuid)
                .collect(Collectors.toList());
    }

    @Override
    public String findDataByUuid(UUID uuid) {
        return map.get(uuid);
    }

    @Override
    public List<String> findAllDataByUuidList(List<UUID> uuidList) {
        return uuidList.stream()
                .map(map::get)
                .collect(Collectors.toList());
    }
}
