package lab;

public class lab_builder {
    public static void main(String[] args) {
        IComputerBuilder officeBuilder = new OfficeComputerBuilder();
        ComputerDirector director = new ComputerDirector(officeBuilder);
        director.constructComputer();
        Computer officeComputer = director.getComputer();
        System.out.println(officeComputer);

        IComputerBuilder gamingBuilder = new GamingComputerBuilder();
        director = new ComputerDirector(gamingBuilder);
        director.constructComputer();
        Computer gamingComputer = director.getComputer();
        System.out.println(gamingComputer);
    }
}


class Computer {
    public String CPU;
    public String RAM;
    public String storage;
    public String GPU;
    public String OS;

    public Computer(String CPU, String RAM, String storage, String GPU, String OS) {
        this.CPU = CPU;
        this.RAM = RAM;
        this.storage = storage;
        this.GPU = GPU;
        this.OS = OS;
    }

    @Override
    public String toString() {
        return "Компьютер: CPU - " + CPU +
                ", RAM - " + RAM +
                ", Накопитель - " + storage +
                ", GPU - " + GPU +
                ", ОС - " + OS;
    }
}

interface IComputerBuilder {
    void buildCPU();

    void buildRAM();

    void buildStorage();

    void buildGPU();

    void buildOS();

    Computer getComputer();
}


class OfficeComputerBuilder implements IComputerBuilder {
    private String CPU;
    private String RAM;
    private String storage;
    private String GPU;
    private String OS;

    @Override
    public void buildCPU() {
        CPU = "Intel i3";
    }

    @Override
    public void buildRAM() {
        RAM = "8GB";
    }

    @Override
    public void buildStorage() {
        storage = "1TB HDD";
    }

    @Override
    public void buildGPU() {
        GPU = "Integrated";
    }

    @Override
    public void buildOS() {
        OS = "Windows 10";
    }

    @Override
    public Computer getComputer() {
        return new Computer(CPU, RAM, storage, GPU, OS);
    }
}

class GamingComputerBuilder implements IComputerBuilder {
    private String CPU;
    private String RAM;
    private String storage;
    private String GPU;
    private String OS;

    @Override
    public void buildCPU() {
        CPU = "Intel i9";
    }

    @Override
    public void buildRAM() {
        RAM = "32GB";
    }

    @Override
    public void buildStorage() {
        storage = "1TB SSD";
    }

    @Override
    public void buildGPU() {
        GPU = "NVIDIA RTX 3080";
    }

    @Override
    public void buildOS() {
        OS = "Windows 11";
    }

    @Override
    public Computer getComputer() {
        return new Computer(CPU, RAM, storage, GPU, OS);
    }
}

class ComputerDirector {
    private final IComputerBuilder builder;

    public ComputerDirector(IComputerBuilder builder) {
        this.builder = builder;
    }

    public void constructComputer() {
        builder.buildCPU();
        builder.buildRAM();
        builder.buildStorage();
        builder.buildGPU();
        builder.buildOS();
    }

    public Computer getComputer() {
        return builder.getComputer();
    }
}
