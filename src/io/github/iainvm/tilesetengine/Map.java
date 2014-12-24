package io.github.iainvm.tilesetengine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

public class Map implements Serializable{
		//The environment which is drawn around the player
		
		private String name;
		private Vector<Integer> dims = new Vector<Integer>(3);
		private Vector<Vector<Vector<Integer>>> matrix;
		private static final long serialVersionUID = 2L;

		public Map(){
			this.name = "default";
			this.dims.setSize(3);
			this.setDims(1, 2, 2);
			this.setNullMap();
		}

		public Map(String name){
			//used for loading default test map
			this.name = name;
			this.dims.setSize(3);
			try {
				this.read();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public Map(String name, int z, int y, int x){
			this.name = name;
			this.dims.setSize(3);
			this.setDims(z, y, x);
			this.setNullMap();
		}
		
		public void setNullMap(){
			
			this.matrix = new Vector<Vector<Vector<Integer>>>(this.dims.get(0));
			this.matrix.setSize(this.dims.get(0));
			for(int i = 0; i < this.dims.get(0); i++){
				
				this.matrix.set(i, new Vector<Vector<Integer>>(this.dims.get(1)));
				this.matrix.get(i).setSize(this.dims.get(1));
				
				for(int j = 0; j < this.dims.get(1); j++){
					this.matrix.get(i).set(j, new Vector<Integer>(this.dims.get(2)));
					this.matrix.get(i).get(j).setSize(this.dims.get(2));
				}
			}
		}
		
		public void setName(String name){
			this.name = name;
		}
		
		public String getName(){
			return this.name;
		}
		
		public void setDims(int z, int y, int x){
			this.dims.set(0, z);
			this.dims.set(1, y);
			this.dims.set(2, x);
		}
		
		public Vector<Integer> getDims(){
			return (Vector<Integer>) this.dims;
		}
		
		public void setTile(int z, int y, int x, int val){
			this.matrix.get(z).get(y).set(x, val);
		}
		
		public Integer getTile(int z, int y, int x){
			return this.matrix.get(z).get(y).get(x);
		}
		
		private void read(){
			FileInputStream fin;
			try {
				fin = new FileInputStream("maps/" + this.name + "/" + this.name + ".ser");
			ObjectInputStream ois = new ObjectInputStream(fin);
			Map o = (Map) ois.readObject();
			this.name = o.name;
			this.dims = o.dims;
			this.matrix = o.matrix;
			ois.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
		public void saveMap(){
			this.write();
		}
	    
		private void write(){
			File f = new File("maps/" + this.name + "/" + this.name + ".ser");
			try{
				f.getParentFile().mkdirs();
				FileOutputStream fout = new FileOutputStream(f);
			    ObjectOutputStream oos = new ObjectOutputStream(fout);
				oos.writeObject(this);
				oos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}