package piccompresse;

import java.io.File;
import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.RelativeSize;
import net.coobird.thumbnailator.resizers.Resizer;


public class Thumbnailator {
	public static void main(String[] args) throws IOException {
		//http://hao.jobbole.com/thumbnailator/
		Thumbnails.of("C:\\Users\\Administrator\\Desktop\\da.jpg")
		.size(300, 300)
        .toFile("C:\\Users\\Administrator\\Desktop\\da_t.jpg");
	}
}
