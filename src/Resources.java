import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Resources {
	private static Resources sharedResource = new Resources();
	 private ColorImage currentImage;
	 private String name;
	 private ArrayList<String> filters;
	 private boolean finished;
	 
	 private Resources() {
		 this.filters = new ArrayList<String>();
		 this.name = "";
		 this.currentImage = null;
		 this.finished = false;
	     //this.currentImage = new ColorImage(new BufferedImage(0,0,0));
	 }
	 
	 public static Resources getSharedResources() {
		 if (sharedResource == null) {
			 sharedResource = new Resources();
		 }
		 return sharedResource;
	 }
	 
	 public ArrayList<String> getFilters() {
		 return filters;
	 }
	 
	 public String getName() {
		 return name;
	 }
	 
	 public ColorImage getCurrentImage() {
		 return currentImage;
	 }
	 
	 public void setName(String newName) {
		 name = newName;
	 }
	 
	 public void addFilter(int filterNum, String filtAdd) {
		 filters.set(filterNum, filtAdd);
	 }
	 
	 public void removeFilter(String filtRem) {
		 for (String filter : filters) {
			 if (filter.equals(filtRem)){
				 filters.remove(filter);
			 }
		 }
	 }
	 
	 public void setImage(ColorImage updatedImage) {
		 currentImage = updatedImage;
	 }
	 
	 public void setFinished(boolean fin) {
		 finished = fin;
	 }
	 
	 public boolean getFinished() {
		 return finished;
	 }
	 
}
