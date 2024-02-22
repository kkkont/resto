module com.resto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.resto to javafx.fxml;
    exports com.resto;
}