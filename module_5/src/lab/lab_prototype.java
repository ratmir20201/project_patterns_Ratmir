package lab;

import java.util.ArrayList;
import java.util.List;

public class lab_prototype {
    public static void main(String[] args) {
        Document original = new Document("Отчёт", "Основное содержание");
        original.addSection(new Section("Введение", "Описание цели документа"));
        original.addSection(new Section("Результаты", "Результаты эксперимента"));
        original.addImage(new Image("https://example.com/image1.png"));

        System.out.println("Оригинал:");
        System.out.println(original);

        DocumentManager manager = new DocumentManager();
        Document copy = manager.createDocument(original);
        copy.addImage(new Image("new URL"));

        System.out.println("\nКопия:");
        System.out.println(copy);

        System.out.println("\n" + original);
    }
}

interface IPrototype<T> {
    T clone();
}


class Document implements IPrototype<Document> {
    private String title;
    private String content;
    private List<Section> sections = new ArrayList<>();
    private List<Image> images = new ArrayList<>();

    public Document(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public void addImage(Image image) {
        images.add(image);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<Section> getSections() {
        return sections;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Document clone() {
        Document cloned = new Document(this.title, this.content);
        for (Section s : sections) {
            cloned.addSection(s.clone());
        }
        for (Image i : images) {
            cloned.addImage(i.clone());
        }
        return cloned;
    }

    @Override
    public String toString() {
        return "Document{" + "title='" + title + '\'' + ", content='" + content + '\'' + ", sections=" + sections + ", images=" + images + '}';
    }
}

class Section implements IPrototype<Section> {
    private String title;
    private String text;

    public Section(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Section clone() {
        return new Section(this.title, this.text);
    }

    @Override
    public String toString() {
        return "Section{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

class Image implements IPrototype<Image> {
    private String url;

    public Image(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Image clone() {
        return new Image(this.url);
    }

    @Override
    public String toString() {
        return "Image{" +
                "url='" + url + '\'' +
                '}';
    }
}

class DocumentManager {
    public Document createDocument(IPrototype<Document> prototype) {
        return prototype.clone();
    }
}


