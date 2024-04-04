package irctc.services;

import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import irctc.entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TrainService {

    private List<Train> trainList;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String TRAIN_DB_PATH = "app/src/main/java/irctc/localDB/trains.json";

    public TrainService() throws IOException {
        File train = new File(TRAIN_DB_PATH);
        trainList = objectMapper.readValue(train, new TypeReference<List<Train>>() {
        });

    }

    public List<Train> searchTains(String source, String destination) {

        return trainList.stream().filter(train -> validTrain(train, source, destination)).collect(Collectors.toList());
    }

    private boolean validTrain(Train train, String source, String destination) {
        List<String> stationOrder = train.getStations();

        int sourceIndex = stationOrder.indexOf(source.toLowerCase());
        int destinationIndex = stationOrder.indexOf(destination.toLowerCase());

        return sourceIndex != -1 && destinationIndex != -1 && sourceIndex < destinationIndex;
    }

}
