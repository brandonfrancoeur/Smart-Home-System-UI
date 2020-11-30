package ca.uvic.seng330.assn3;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class layoutSetup {



    public static GridPane createAndConfigurePane(GridPane view2) {
        view2 = new GridPane();

        ColumnConstraints leftCol = new ColumnConstraints();
        leftCol.setHalignment(HPos.RIGHT);
        leftCol.setHgrow(Priority.NEVER);

        ColumnConstraints rightCol = new ColumnConstraints();
        rightCol.setHgrow(Priority.SOMETIMES);

        view2.getColumnConstraints().addAll(leftCol, rightCol);

        view2.setAlignment(Pos.CENTER);
        view2.setHgap(5);
        view2.setVgap(10);
        return view2;
    }
}
