package com.inventorymanagement.controller;//package com.inventorymanagement.controller;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Objects;
//
//@RestController
//@RequestMapping("api/v1/Inventory-Management")
//public class UpdateItemController {
//    private String name;
//    private String id;
//    private String category;
//    private boolean available;
//    private int quantity;
//    private String location;
//
//
//    public UpdateItemController() {}
//
//    public UpdateItemController(String name, String id, String category,
//                             boolean available, int quantity, String location) {
//        this.name = name;
//        this.id = id;
//        this.category = category;
//        this.available = available;
//        this.quantity = quantity;
//        this.location = location;
//    }
//
//    public UpdateItemController(UpdateItemController.Builder builder) {
//        this.name = builder.name;
//        this.id = builder.id;
//        this.category = builder.category;
//        this.available = builder.available;
//        this.quantity = builder.quantity;
//        this.location = builder.location;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public boolean isAvailable() {
//        return available;
//    }
//
//    public void setAvailable(boolean available) {
//        this.available = available;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    @Override
//    public String toString() {
//        return "UpdateItemController{" +
//                "name='" + name + '\'' +
//                ", id='" + id + '\'' +
//                ", category='" + category + '\'' +
//                ", available=" + available +
//                ", quantity=" + quantity +
//                ", location='" + location + '\'' +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null ||this.getClass() != o.getClass()) return false;
//        UpdateItemController that = (UpdateItemController) o;
//        return Objects.equals(name, that.name) && Objects.equals(id, that.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, id);
//    }
//
//    public static UpdateItemController.Builder builder() {
//        return new UpdateItemController.Builder();
//    }
//
//    public static final class Builder {
//        private String name;
//        private String id;
//        private String category;
//        private boolean available;
//        private int quantity;
//        private String location;
//
//        public UpdateItemController.Builder withName(String nameToUse) {
//            this.name = nameToUse;
//            return this;
//        }
//
//        public UpdateItemController.Builder withId(String idToUse) {
//            this.id = idToUse;
//            return this;
//        }
//
//        public Builder withCategory(String categoryToUse) {
//            this.category = categoryToUse;
//            return this;
//        }
//
//        public Builder withAvailable(boolean availableToUse) {
//            this.available = availableToUse;
//            return this;
//        }
//
//        public Builder withQuantity(int quantityToUse) {
//            this.quantity = quantityToUse;
//            return this;
//        }
//
//        public Builder withLocation(String locationToUse) {
//            this.location = locationToUse;
//            return this;
//        }
//
//        public UpdateItemController build() {
//            return new UpdateItemController(this);
//        }
//    }
//}
