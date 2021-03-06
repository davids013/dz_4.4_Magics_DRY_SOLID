package product;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Product implements Comparable, Cloneable {
    private static final String[] HEADER = {
            String.format("%-15s",  "Название"),
            String.format("%-19s",  "Ном.номер"),
            String.format("%-11s",  "Цена,руб"),
            String.format("%-10s",  "Склад,шт"),
            String.format("%-5s",   "Вес,кг"),
            String.format("%-13s",  "Категория"),
            String.format("%-8s",   "Цвет"),
            String.format("%-13s",  "Описание")};
    private String title;
    private long partNumber;
    private int price;
    private int stock;
    private float weight;
    private String description;
    private Category category;
    private Color color;

    public static Product getRandomProduct() {
//        Magics
        final int TITLE_LENGTH = 10;
        final int CYRILLIC_LENGTH = 33;
        final int CYRILLIC_FIRST = 1072;
        final int MAX_PRICE = 10_000;
        final int MAX_STOCK = 1_000;
        final float MAX_WEIGHT = 20f;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TITLE_LENGTH; i++) {
            sb.append((char) (Math.random() * CYRILLIC_LENGTH + CYRILLIC_FIRST - 1));
        }
        sb.replace(0, 1, sb.substring(0, 1).toUpperCase());
        String title = sb.toString();
        return new Product(title, (long) (Math.random() * Long.MAX_VALUE))
                .setCategory(Category.values()[(int) (Math.random() * Category.values().length)])
                .setPrice((int) (Math.random() * MAX_PRICE))
                .setStock((int) (Math.random() * MAX_STOCK))
                .setWeight((float) Math.random() * MAX_WEIGHT)
                .setColor(Color.values()[(int) (Math.random() * Color.values().length)]);
    }

    public static List<String> getHeader() {
        return Arrays.asList(HEADER);
    }

    public Product(String title, long partNumber) {
        this.title = title;
        this.partNumber = partNumber;
        description = "не добавлено";
    }

    public String getTitle() {
        return title;
    }

    public Product setTitle(String title) {
        if (title.trim().length() > 0) this.title = title;
        return this;
    }

    public long getPartNumber() {
        return partNumber;
    }

    public Product setPartNumber(long partNumber) {
        if (partNumber > 0) this.partNumber = partNumber;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Product setPrice(int price) {
        if (price > 0) this.price = price;
        return this;
    }

    public int getStock() {
        return stock;
    }

    public Product setStock(int stock) {
        if (stock >= 0) this.stock = stock;
        return this;
    }

    public float getWeight() {
        return weight;
    }

    public Product setWeight(float weight) {
        if (weight > 0) this.weight = weight;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public String getCategoryString() {
        return switch (category) {
            case DISHES     -> String.format("%-13s", "посуда");
            case CHEMICALS  -> String.format("%-13s", "бытовая химия");
            case CLOTHES    -> String.format("%-13s", "одежда");
            case FOOD       -> String.format("%-13s", "еда");
            case OTHER      -> String.format("%-13s", "прочее");
            default         -> String.format("%-13s", "не указано");
        };
    }

    public Product setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Color getColor() {
        return color;
    }

    public String getColorString() {
        return switch (color) {
            case RED    -> String.format("%-9s", "красный  ");
            case GREEN  -> String.format("%-9s", "зелёный  ");
            case BLUE   -> String.format("%-9s", "синий    ");
            case YELLOW -> String.format("%-9s", "жёлтый   ");
            case WHITE  -> String.format("%-9s", "белый    ");
            case BLACK  -> String.format("%-9s", "чёрный   ");
            case GREY   -> String.format("%-9s", "серый    ");
            default     -> String.format("%-9s", "не указан");
        };
    }

    public Product setColor(Color color) {
        this.color = color;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return partNumber == product.partNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(partNumber);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(String.format("%-15s", getTitle()))
                .append(String.format(", %-19s", getPartNumber()))
                .append(String.format(", %-11s", getPrice()))
                .append(String.format(", %-10s", getStock()))
                .append(String.format(", %.3f", getWeight()))
                .append(String.format(", %-13s", getCategoryString()))
                .append(String.format(", %-8s", getColorString()))
                .append(String.format(", %-13s", getDescription()));
        return sb.toString();
    }

    @Override
    public Product clone() {
        try {
            return (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int compareTo(Object o) {
        return title.toLowerCase().compareTo(((Product) o).getTitle().toLowerCase());
    }
}
