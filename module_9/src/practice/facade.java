package practice;

public class facade {
    public static void main(String[] args) {
        HotelFacade hotel = new HotelFacade();

        hotel.bookRoomWithFoodAndCleaning();
        System.out.println();

        hotel.organizeEvent();
        System.out.println();

        hotel.bookRestaurantWithTaxi();
        System.out.println();
    }
}

class RoomBookingSystem {
    public void book() {
        System.out.println("Номер забронирован.");
    }

    public void cancelBook() {
        System.out.println("Бронирование отменено.");
    }

    public boolean checkIsAvailable() {
        System.out.println("Проверка доступности номеров...");
        return true;
    }
}

class RestaurantSystem {
    public void book() {
        System.out.println("Столик забронирован.");
    }

    public void orderFood() {
        System.out.println("Еда заказана.");
    }
}

class EventManagementSystem {
    public void book() {
        System.out.println("Конференц-зал забронирован.");
    }

    public void orderEquipment() {
        System.out.println("Оборудование заказано.");
    }
}

class CleaningService {
    public void scheduleCleaning() {
        System.out.println("Уборка запланирована.");
    }

    public void performCleaning() {
        System.out.println("Уборка выполнена.");
    }
}

class HotelFacade {
    private RoomBookingSystem roomBooking;
    private RestaurantSystem restaurant;
    private EventManagementSystem eventManagement;
    private CleaningService cleaning;

    public HotelFacade() {
        this.roomBooking = new RoomBookingSystem();
        this.restaurant = new RestaurantSystem();
        this.eventManagement = new EventManagementSystem();
        this.cleaning = new CleaningService();
    }

    public void bookRoomWithFoodAndCleaning() {
        if (roomBooking.checkIsAvailable()) {
            roomBooking.book();
            restaurant.orderFood();
            cleaning.scheduleCleaning();
        }
        System.out.println("Все услуги оформлены.");
    }

    public void organizeEvent() {
        eventManagement.book();
        eventManagement.orderEquipment();
        roomBooking.book();
        System.out.println("Мероприятие организовано.");
    }

    public void bookRestaurantWithTaxi() {
        restaurant.book();
        System.out.println("Такси заказано.");
        System.out.println("Стол и такси оформлены.");
    }
}