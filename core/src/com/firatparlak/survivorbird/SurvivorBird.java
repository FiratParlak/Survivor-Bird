package com.firatparlak.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.google.android.gms.ads.*;

import java.util.Random;

public class SurvivorBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background,background1,background2,background3,background4,background5,background6,background7,background8,background9,background10,background11,background12,background13,background14,background15;
	Texture bird;
	Texture heart1,heart2,heart3;
	Texture bee1,bee1_1;
	Texture frame1,frame2,frame3,frame4,frame5,frame6;

	float birdX = 0;
	float birdY = 0;
	int gameState = 0;
	float velocity = 0;
	float gravity = 0.1f;
	float enemyVelocity = 2;
	Random random;
	int score = 0;
	int can=0;
	int scoredEnemy = 0;
	int sayi=0;
	BitmapFont font;
	BitmapFont font2;
	Animation ucak1,ucak2,ucak3,ucak1_1,ucak2_2,ucak3_3;
	float gecenZaman=0;
	private Music backmusic;
	private Sound patlama;
	private TextureRegion gameOverResim;
	Animation Anibackground;

	Circle birdCircle;

	ShapeRenderer shapeRenderer;

	int numberOfEnemies = 4;
	float [] enemyX = new float[numberOfEnemies];
	float [] enemyOffSet = new float[numberOfEnemies];
	float [] enemyOffSet2 = new float[numberOfEnemies];
	float [] enemyOffSet3 = new float[numberOfEnemies];
	float distance = 0;

	Circle[] enemyCircles;
	Circle[] enemyCircles2;
	Circle[] enemyCircles3;

   //reklam
   InterstitialAd mInterstitialAd;


	@Override
	//başlangıçta ekrana çizim yapamızı sağlar..
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("full-background.png");
		/*background1 = new Texture("bbackgound.png");
		background2 = new Texture("bbackgound1.png");
		background3 = new Texture("bbackgound2.png");
		background4 = new Texture("bbackgound3.png");
		background5 = new Texture("bbackgound4.png");
		background6 = new Texture("bbackgound5.png");
		background7 = new Texture("bbackgound6.png");
		background8 = new Texture("bbackgound7.png");
		background9 = new Texture("bbackgound8.png");
		background10 = new Texture("backgound9.png");
		background11 = new Texture("bbackgound9.png");
		background12 = new Texture("bbackgound10.png");
		background13 = new Texture("bbackgound11.png");
		background14 = new Texture("bbackgound12.png");
		background15 = new Texture("bbackgound13.png");
		Anibackground=new Animation(2.05f,new TextureRegion(background1),new TextureRegion(background2),new TextureRegion(background3),new TextureRegion(background4),new TextureRegion(background5),new TextureRegion(background6)
				,new TextureRegion(background7),new TextureRegion(background8),new TextureRegion(background9),new TextureRegion(background10),new TextureRegion(background11),new TextureRegion(background12),new TextureRegion(background13),new TextureRegion(background14),new TextureRegion(background15));
		Anibackground.setPlayMode(Animation.PlayMode.LOOP);
*/
		bird = new Texture("bird.png");

		bee1 = new Texture("bee1.png");
		bee1_1 = new Texture("bee2.png");

		heart1 = new Texture("heart.png");
		heart2 = new Texture("heart.png");
		heart3 = new Texture("heart.png");
		can=1;

		gameOverResim=new TextureRegion(new Texture("gameover.png"));
		patlama=Gdx.audio.newSound(Gdx.files.internal("patlama.wav"));
		backmusic=Gdx.audio.newMusic(Gdx.files.internal("backsound.mp3"));
		backmusic.setLooping(true);
		backmusic.play();

		ucak1 =new Animation(0.05f,new TextureRegion(bee1),new TextureRegion(bee1_1));
		ucak1.setPlayMode(Animation.PlayMode.LOOP);

		ucak2 =new Animation(0.05f,new TextureRegion(bee1),new TextureRegion(bee1_1));
		ucak2.setPlayMode(Animation.PlayMode.LOOP);

		ucak3 =new Animation(0.05f,new TextureRegion(bee1),new TextureRegion(bee1_1));
		ucak3.setPlayMode(Animation.PlayMode.LOOP);


		frame1 = new Texture("frame1.png");
		frame2 = new Texture("frame2.png");
		frame3 = new Texture("frame3.png");
		frame4 = new Texture("frame4.png");
		frame5 = new Texture("frame5.png");
		frame6 = new Texture("frame6.png");

		ucak1_1 =new Animation(0.05f,new TextureRegion(frame1),new TextureRegion(frame2),new TextureRegion(frame3),new TextureRegion(frame4),new TextureRegion(frame5),new TextureRegion(frame6));
		ucak1_1.setPlayMode(Animation.PlayMode.LOOP);

		ucak2_2= new Animation(0.05f,new TextureRegion(frame1),new TextureRegion(frame2),new TextureRegion(frame3),new TextureRegion(frame4),new TextureRegion(frame5),new TextureRegion(frame6));
		ucak2_2.setPlayMode(Animation.PlayMode.LOOP);

		ucak3_3 =new Animation(0.05f,new TextureRegion(frame1),new TextureRegion(frame2),new TextureRegion(frame3),new TextureRegion(frame4),new TextureRegion(frame5),new TextureRegion(frame6));
		ucak3_3.setPlayMode(Animation.PlayMode.LOOP);




		distance = Gdx.graphics.getWidth() / 2;
		random = new Random();


		birdX = Gdx.graphics.getWidth() / 2 - bird.getHeight() / 2;
		birdY = Gdx.graphics.getHeight() / 3;

		shapeRenderer = new ShapeRenderer();


		birdCircle = new Circle();
		enemyCircles = new Circle[numberOfEnemies];
		enemyCircles2 = new Circle[numberOfEnemies];
		enemyCircles3 = new Circle[numberOfEnemies];


		font = new BitmapFont();
		font.setColor(Color.GREEN);
		font.getData().setScale(4);

		font2 = new BitmapFont();
		font2.setColor(Color.YELLOW);
		font2.getData().setScale(6);


		for (int i = 0; i<numberOfEnemies; i++) {

            //arıların y'leri arasındaki uzaklık
			enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
			enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
			enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);

			enemyX[i] = Gdx.graphics.getWidth() - bee1.getWidth() / 2 + i * distance;//arı gruparın x leri arasındaki uzaklık


			enemyCircles[i] = new Circle();
			enemyCircles2[i] = new Circle();
			enemyCircles3[i] = new Circle();

		}




	}

	@Override
	//oyun çalıştıgı srece bu metod çagrılır..
	public void render () {

		batch.begin();
		float delTime=Gdx.graphics.getDeltaTime();
		gecenZaman+=delTime;
		batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		if(can==1){
			batch.draw(heart1,Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/10,Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/11);
		}
		if(can==2){
			batch.draw(heart1,Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/10,Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/11);

			batch.draw(heart2,Gdx.graphics.getWidth()-2*Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/10,Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/11);
		}
		if(can==3){
			batch.draw(heart1,Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/10,Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/11);

			batch.draw(heart2,Gdx.graphics.getWidth()-2*Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/10,Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/11);
			batch.draw(heart2,Gdx.graphics.getWidth()-3*Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/10,Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/11);
		}
		//batch.draw(heart,Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/10,Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
		if (gameState ==1) {



			if (enemyX[scoredEnemy] < Gdx.graphics.getWidth() / 2 - bird.getHeight() / 2) {
				score++;
                if(score==3)
                	can+=1;
                else if(score==10){
                	can+=1;
				}

				if (scoredEnemy < numberOfEnemies - 1) {
					scoredEnemy++;

				} else {
					scoredEnemy = 0;
				}

			}



			if (Gdx.input.justTouched()) {

				velocity = -5;//arının zıplama hızı

			}


			for (int i = 0; i < numberOfEnemies; i++) {


				if (enemyX[i] < 0) {
					enemyX[i] = enemyX[i] + numberOfEnemies * distance;

					enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);


				} else {
					enemyX[i] = enemyX[i] - enemyVelocity;
				}

              if(i%2==0){
				  batch.draw((TextureRegion) ucak1_1.getKeyFrame(gecenZaman),enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet[i],Gdx.graphics.getWidth() / 12,Gdx.graphics.getHeight() / 10);
				  batch.draw((TextureRegion) ucak2_2.getKeyFrame(gecenZaman),enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet2[i],Gdx.graphics.getWidth() / 12,Gdx.graphics.getHeight() / 10);
				  batch.draw((TextureRegion) ucak3_3.getKeyFrame(gecenZaman),enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet3[i],Gdx.graphics.getWidth() / 12,Gdx.graphics.getHeight() / 10);
			  }
			  else if(i%2==1){
				  batch.draw((TextureRegion) ucak1.getKeyFrame(gecenZaman),enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet[i],Gdx.graphics.getWidth() / 12,Gdx.graphics.getHeight() / 10);
				  batch.draw((TextureRegion) ucak2.getKeyFrame(gecenZaman),enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet2[i],Gdx.graphics.getWidth() / 12,Gdx.graphics.getHeight() / 10);
				  batch.draw((TextureRegion) ucak3.getKeyFrame(gecenZaman),enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet3[i],Gdx.graphics.getWidth() / 12,Gdx.graphics.getHeight() / 10);
			  }
				 //	batch.draw(bee1,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet[i],Gdx.graphics.getWidth() / 15,Gdx.graphics.getHeight() / 10);
			    //	batch.draw(bee2,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet2[i],Gdx.graphics.getWidth() / 15,Gdx.graphics.getHeight() / 10);
			    //	batch.draw(bee3,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet3[i],Gdx.graphics.getWidth() / 15,Gdx.graphics.getHeight() / 10);


				enemyCircles[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 30,  Gdx.graphics.getHeight()/2 + enemyOffSet[i] + Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() / 30);
				enemyCircles2[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 30,  Gdx.graphics.getHeight()/2 + enemyOffSet2[i] + Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() / 30);
				enemyCircles3[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 30,  Gdx.graphics.getHeight()/2 + enemyOffSet3[i] + Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() / 30);


			}



			if (birdY > 0 && birdY < Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/10) {
				velocity = velocity + gravity;
				birdY = birdY - velocity;

			}
			else if (birdY > Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/10){
				gameState = 2;
				patlama.play();
				//can-=1;
			}
			else{
				gameState = 2;
				patlama.play();
				//can-=1;
			}


		} else if (gameState == 0) {
			if (Gdx.input.justTouched()) {
				gameState = 1;
				can=1;
			}
		} else if (gameState == 2) {

			//font2.draw(batch,"Game Over!",Gdx.graphics.getWidth() /3,Gdx.graphics.getHeight() / 2);
			batch.draw(gameOverResim,Gdx.graphics.getWidth()/2-gameOverResim.getRegionWidth()/2,Gdx.graphics.getHeight()/2-gameOverResim.getRegionHeight()/2);
			if(can==1){
				font2.draw(batch,"Score : "+score,Gdx.graphics.getWidth() /3,(Gdx.graphics.getHeight() / 2)+Gdx.graphics.getHeight() / 4);
			}
			if (Gdx.input.justTouched()) {
				gameState = 1;
				can-=1;

				birdY = Gdx.graphics.getHeight() / 3;


				for (int i = 0; i<numberOfEnemies; i++) {


					enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);

					enemyX[i] = Gdx.graphics.getWidth() - bee1.getWidth() / 2 + i * distance;


					enemyCircles[i] = new Circle();
					enemyCircles2[i] = new Circle();
					enemyCircles3[i] = new Circle();

				}

				velocity = 0;
				scoredEnemy = 0;
				//can degeri 0 oldugunda skoru 0 yap.
				if(can==0){
					score = 0;
					can=1;

				}


			}

		}


		batch.draw(bird,birdX, birdY, Gdx.graphics.getWidth() / 11,Gdx.graphics.getHeight() / 8);

		font.draw(batch,String.valueOf(score),100,200);

		batch.end();

		birdCircle.set(birdX +Gdx.graphics.getWidth() / 30 ,birdY + Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() / 30);

		//shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		//shapeRenderer.setColor(Color.BLACK);
		//shapeRenderer.circle(birdCircle.x,birdCircle.y,birdCircle.radius);




		for ( int i = 0; i < numberOfEnemies; i++) {
			//shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth() / 30,  Gdx.graphics.getHeight()/2 + enemyOffSet[i] + Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() / 30);
			//shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth() / 30,  Gdx.graphics.getHeight()/2 + enemyOffSet2[i] + Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() / 30);
			//shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth() / 30,  Gdx.graphics.getHeight()/2 + enemyOffSet3[i] + Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() / 30);
            //burada çarpışmaları kontrol ediyor..
			if (Intersector.overlaps(birdCircle,enemyCircles[i]) || Intersector.overlaps(birdCircle,enemyCircles2[i]) || Intersector.overlaps(birdCircle,enemyCircles3[i])) {
				gameState = 2;
			    int a=0;
				if(Intersector.overlaps(birdCircle,enemyCircles[i])){

					if(a==0){
						//patlama.play();
					}
					a=1;
				}
				else if(Intersector.overlaps(birdCircle,enemyCircles2[i])){

					if(a==0){
						//patlama.play();
					}
					a=1;
				}
				else if(Intersector.overlaps(birdCircle,enemyCircles3[i])){

					if(a==0){
						//patlama.play();
					}
					a=1;
				}

			}
		}

		//shapeRenderer.end();

	}

	@Override
	public void dispose () {

	}
}