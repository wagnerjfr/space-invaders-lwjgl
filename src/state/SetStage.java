package state;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class SetStage {

    private HashMap<Integer, Stage> listStage = new HashMap<Integer, Stage>();

    public SetStage() throws IOException {
        readStageFile();
    }

    private void readStageFile() throws IOException {
        BufferedReader buffer = null;

        String[] data;
        String line;

        buffer = new BufferedReader(new FileReader("res/stages/stages1.csv"));

        line = buffer.readLine(); //Jump the header

        while((line = buffer.readLine()) != null) {
            data = line.split(",");

            int id = Integer.valueOf(data[0].trim());
            int numberTotalOfAliens = Integer.parseInt(data[1].trim());
            int numberOfAliensRow = Integer.parseInt(data[2].trim());
            float speed = Float.parseFloat(data[3].trim());
            float bombSpeed = Float.parseFloat(data[4].trim());
            int hits = Integer.parseInt(data[5].trim());

            listStage.put(id, new Stage(id, numberTotalOfAliens, numberOfAliensRow, speed, bombSpeed, hits));
        }

        if (buffer != null) buffer.close();
    }

    public Stage getStage(int id) {
        return listStage.get(id);
    }

    public class Stage {
        private int id;
        private int numberTotalOfAliens;
        private int numberOfAliensRow;
        private float speed;
        private float bombSpeed;
        private int hits;

        public Stage(int id, int numberTotalOfAliens, int numberOfAliensRow, float speed, float bombSpeed, int hits) {
            this.id = id;
            this.numberTotalOfAliens = numberTotalOfAliens;
            this.numberOfAliensRow = numberOfAliensRow;
            this.speed = speed;
            this.bombSpeed = bombSpeed;
            this.hits = hits;
        }

        public int getId() {
            return id;
        }

        public int getNumberTotalOfAliens() {
            return numberTotalOfAliens;
        }

        public int getNumberOfAliensRow() {
            return numberOfAliensRow;
        }

        public float getSpeed() {
            return speed;
        }

        public float getBombSpeed() {
            return bombSpeed;
        }

        public int getHits() {
            return hits;
        }
    }
}
