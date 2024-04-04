package irctc.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import irctc.entities.Train;
import irctc.entities.User;
import util.UserServiceUtil;

public class UserBookingService {
    private User user;

    private List<User> userList;

    private ObjectMapper OBJECTMAPPER = new ObjectMapper();

    private static final String USER_PATH = "app/src/main/java/irctc/localDB/User.json";

    public UserBookingService(
            User user1) throws IOException {
        this.user = user1;
        loadUsers();

    }

    public UserBookingService() throws IOException {
        loadUsers();
    }

    public List<User> loadUsers() throws IOException {
        File users = new File(USER_PATH);
        return OBJECTMAPPER.readValue(users, new TypeReference<List<User>>() {
        });
    }

    // If we can`t find user null pointer exception should not be thrown that`s why
    // Optional
    public Boolean loginUser() {
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getnameString().equalsIgnoreCase(user.getnameString())
                    && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1) {
        try {
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (Exception e) {
            // TODO: handle exception
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File userFile = new File(USER_PATH);
        OBJECTMAPPER.writeValue(userFile, userList);
    }

    public void fetchBooking() {
        user.printTicket();
    }

    public Boolean cancelBooking(String ticketId) {
        // Checking if the ticketId exists in the system
        Optional<User> userOptional = userList.stream().filter(user -> user.hasTicket(ticketId)).findFirst();
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.removeTicket(ticketId);
            return true;
        }
        return false;
    }

    public List<Train> getTrains(String source, String destination) {
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTains(source, destination);
        } catch (Exception e) {
            // TODO: handle exception
            return new ArrayList<>();
        }
    }

    public List<List<Integer>> fetchSeat(Train train) {
        return train.getSeats();
    }

    public Boolean bookTrainSeat(Train train, int row, int seat){
        try {
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();

            if(row >= 0 && row < seats.size() && seat >= && seat < seats.get(row).size()){
                if(seats.get(row).get(seat)==0){
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);

                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
