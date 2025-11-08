package homework;

public class facade {
    public static void main(String[] args) {
        TV tv = new TV();
        AudioSystem audio = new AudioSystem();
        DVDPlayer dvd = new DVDPlayer();
        GameConsole console = new GameConsole();

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(tv, audio, dvd, console);

        homeTheater.watchMovie("Аниме");
        homeTheater.endMovie();

        System.out.println();

        homeTheater.playGame("Snowrunner");
        homeTheater.endGame();

    }
}

class TV {
    public void on() {
        System.out.println("TV включен");
    }

    public void off() {
        System.out.println("TV выключен");
    }

    public void setChannel(String channel) {
        System.out.println("TV переключен на канал: " + channel);
    }
}

class AudioSystem {
    public void on() {
        System.out.println("Аудиосистема включена");
    }

    public void off() {
        System.out.println("Аудиосистема выключена");
    }

    public void setVolume(int level) {
        System.out.println("Громкость установлена на: " + level);
    }
}

class DVDPlayer {
    public void play(String movie) {
        System.out.println("Воспроизведение фильма: " + movie);
    }

    public void pause() {
        System.out.println("Фильм на паузе");
    }

    public void stop() {
        System.out.println("Фильм остановлен");
    }
}

class GameConsole {
    public void on() {
        System.out.println("Игровая консоль включена");
    }

    public void startGame(String game) {
        System.out.println("Игра запущена: " + game);
    }

    public void off() {
        System.out.println("Игровая консоль выключена");
    }
}

class HomeTheaterFacade {
    private TV tv;
    private AudioSystem audio;
    private DVDPlayer dvd;
    private GameConsole console;

    public HomeTheaterFacade(TV tv, AudioSystem audio, DVDPlayer dvd, GameConsole console) {
        this.tv = tv;
        this.audio = audio;
        this.dvd = dvd;
        this.console = console;
    }

    public void watchMovie(String movie) {
        System.out.println("Подготовка к просмотру фильма");
        tv.on();
        audio.on();
        audio.setVolume(20);
        dvd.play(movie);
    }

    public void endMovie() {
        System.out.println("Выключение фильма");
        dvd.stop();
        audio.off();
        tv.off();
    }

    public void playGame(String game) {
        System.out.println("Подготовка к игре ");
        console.on();
        audio.on();
        audio.setVolume(15);
        console.startGame(game);
    }

    public void endGame() {
        System.out.println("Завершение игры");
        console.off();
        audio.off();
    }
}

