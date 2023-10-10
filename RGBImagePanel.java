/***
 *** �摜��񏈗��@��2��ۑ�
 ***/

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * RGBImagePanelは、与えられた画像のRGB色の反転を行い、その結果を表示するJPanelです。
 */
public class RGBImagePanel extends JPanel {
	
	int width, height;         // 画像の幅と高さ
	Image image = null;        // 元の画像
	BufferedImage bufImage = null;  // 色反転されたBufferedImage

	/**
	 * コンストラクタ。
	 * 与えられた画像のRGB色の反転を行い、結果をBufferedImageとして保存します。
	 * @param image 反転する元の画像
	 */


	public RGBImagePanel( Image image ){
		this.image = image;
		width = image.getWidth(this);  // 画像の幅を取得
		height = image.getHeight(this);  // 画像の高さを取得
		this.setSize(width, height);  // パネルのサイズを画像のサイズに設定
		
		// 画像をBufferedImageに変換
		bufImage = createBufferedImage(image);

		// 画像の各ピクセルの色を反転
		for (int y = 0; y < bufImage.getHeight(); y++) {
			for (int x = 0; x < bufImage.getWidth(); x++) {
				int color = bufImage.getRGB(x, y);  // 現在のピクセルの色を取得
				int r = 255 - getRed(color);   // 赤成分の反転
				int g = 255 - getGreen(color); // 緑成分の反転
				int b = 255 - getBlue(color);  // 青成分の反転
				// 反転された色をBufferedImageに設定
				bufImage.setRGB(x, y, 255 << 24 | r << 16 | g << 8 | b);
			}
		}
	}

	/**
	 * 与えられたImageをBufferedImageに変換します。
	 * @param img 変換するImage
	 * @return BufferedImageに変換されたイメージ
	 */
	public BufferedImage createBufferedImage(Image img) {
		BufferedImage bimg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics g = bimg.getGraphics();
		g.drawImage(img, 0, 0, null);  // 画像をBufferedImageにコピー
		g.dispose();
		return bimg;
	}

	// 与えられた色から赤成分を取得
	public int getRed(int color) {
		return color >> 16 & 0xff;
	}

	// 与えられた色から緑成分を取得
	public int getGreen(int color) {
		return color >> 8 & 0xff;
	}

	// 与えられた色から青成分を取得
	public int getBlue(int color) {
		return color & 0xff;
	}

	// このパネルに反転色の画像を描画
	@Override
	public void paint(Graphics g) {
		g.drawImage(bufImage, 0, 0, this);
	}
}
