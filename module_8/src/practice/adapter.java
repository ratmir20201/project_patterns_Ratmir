package practice;

public class adapter {
    public static void main(String[] args) {
        IInternalDeliveryService DHLservice = DeliveryServiceFactory.getDeliveryService("DHL");
        DHLservice.deliverOrder("1");
        DHLservice.getDeliveryStatus("1");

        System.out.println();

        IInternalDeliveryService service = DeliveryServiceFactory.getDeliveryService("");
        service.deliverOrder("1");
        service.getDeliveryStatus("1");
    }
}

interface IInternalDeliveryService {
    void deliverOrder(String orderId);

    void getDeliveryStatus(String orderId);
}

class InternalDeliveryService implements IInternalDeliveryService {

    @Override
    public void deliverOrder(String orderId) {
        System.out.println("Доставка заказа");
    }

    @Override
    public void getDeliveryStatus(String orderId) {
        System.out.println("Статус заказа");
    }
}

class ExternalLogisticsServiceA {
    void shipItem(int itemId) {
        System.out.println("Отправка через сторонний класс");
    }

    void trackShipment(int shipmentId) {
        System.out.println("Отслеживание через сторонний класс");
    }
}


class LogisticsAdapterA implements IInternalDeliveryService {
    private ExternalLogisticsServiceA serviceA;

    public LogisticsAdapterA(ExternalLogisticsServiceA serviceA) {
        this.serviceA = serviceA;
    }

    @Override
    public void deliverOrder(String orderId) {
        int itemId = Integer.parseInt(orderId);
        serviceA.shipItem(itemId);
    }

    @Override
    public void getDeliveryStatus(String orderId) {
        int itemId = Integer.parseInt(orderId);
        serviceA.trackShipment(itemId);
    }
}

class DeliveryServiceFactory {
    public static IInternalDeliveryService getDeliveryService(String serviceType) {
        if (serviceType == "DHL") {
            return new LogisticsAdapterA(new ExternalLogisticsServiceA());
        } else {
            return new InternalDeliveryService();
        }
    }
}