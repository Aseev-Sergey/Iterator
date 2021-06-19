package sample;


import javafx.scene.image.Image;
import java.io.File;
import java.util.LinkedList;

public class ImageCollection implements Aggregate {

    private String imgName;
    private String imgDir;
    private String root;
    private LinkedList<String> listDir;
    public Image bi;


    public ImageCollection(String root, String imgName) {

        this.root = root;
        this.imgName = imgName;
        this.imgDir = this.root;
        listDir = new LinkedList<>();
        fillDirs();
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
                if(listDir.isEmpty()){
                    imgDir = root;
                }
                else{
                    imgDir = listDir.removeFirst();
                }
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

    private void fillDirs(){

        File directory = new File(root);
        if(directory.isDirectory()){
            for(File f : directory.listFiles()) {
                if(f.isDirectory())
                    listDir.add(f.getAbsolutePath());
                else {
                }
            }
        }

    }
}
