package sample;


import javafx.scene.image.Image;

public class ImageCollection implements Aggregate {
    private String imgName;
    private String imgDir;
    public Image bi;
    public ImageCollection(String imgDir, String imgName) {
        this.imgName = imgName;
        this.imgDir = imgDir;
    }
    private class ImageIterator implements Iterator {
        private int current=0;
        @Override
        public boolean hasNext() {
            String
                    filename= imgDir + "\\"+ imgName +(current+1)+".PNG";
            try {
                bi = new Image(filename);

                return true;

            } catch (Exception ex) {
                preview();

                return true;
            }
        }
        @Override
        public Object next() {
            if(this.hasNext()){
                current++;
                return bi;
            }

            return null;
        }
        @Override
        public Object back() {
            current--;
            if(this.hasNext()){
                return bi;
            }

            return null;
        }

        @Override
        public Object preview() {
            current=0;
            return current;
        }
    }
    @Override
    public Iterator getIterator() {
        return new ImageIterator();
    }
}
