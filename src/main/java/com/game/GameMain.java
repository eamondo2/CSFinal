
package com.game;


//imports

import com.game.math.Vector3f;
import com.game.structures.*;
import com.game.util.Physics;
import com.game.util.TextureLoader;
import com.game.util.InputHandler;
import com.game.util.SoundUtil;
import org.lwjgl.Sys;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;



/**
 * Created by eamon on 5/17/2015.
 * The majority of the GLFW code is lifted from the lwjgl getting started guide, as GLFW is hard.
 */


//TODO: Make lighting a thing. Like walls with pools of light. Dunno.


public class GameMain {
    Thread musicThread = new Thread();
    static File mainMusic = new File("assets/AdhesiveWombat_8bitAdventure.wav");
    static File hardmodeFile = new File("assets/AdhesiveWombat_Downforce.wav");
    public static File musicFile = mainMusic;
    public Vector3f hardmodeRotations = new Vector3f(0,0,0);
    public static boolean musicPlaying = false;
    public boolean zrotLock = false;
    public boolean xrotLock = false;
    public static boolean hardMode = false;
    public static boolean gameOver = false;
    public boolean gameOversecond = false;
    public Floor f;
    public int score = 0;
    public int counter = 0;
    public ArrayList<GenericRect> renderList;
    public ArrayList<GenericRect> bgRenderList;
	public ArrayList<GenericRect> physList;
	public ArrayList<GenericRect> updateList;
	public MainChar character;
	int WIDTH = 700;
    int HEIGHT = 700;
    //Where to begin?
	//beginnings of lwjgl implementation.
	private GLFWErrorCallback errorCall;
    private InputHandler keyCall;
    private long window;
    private boolean hardModeoverride;
    private boolean eKeyedge = false;


    public static void main(String[] args) {
        String PATH_TO_LIBS = System.getProperty("user.dir")+"/native";
        System.setProperty("org.lwjgl.librarypath", PATH_TO_LIBS);

        new GameMain().run();


	}

	public void run() {
		System.out.println( Sys.getVersion());


		try {
			init();
			loop();

			glfwDestroyWindow(window);
			keyCall.release();
            this.musicThread.stop();
		} finally {
			glfwTerminate();
			errorCall.release();

		}
	}

	public void init() {
		glfwSetErrorCallback(errorCall = errorCallbackPrint(System.err));
		if (glfwInit() != GL11.GL_TRUE)
			throw new IllegalStateException("Unable to initialize GLFW");


		//configure window
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable



		// Create the window
		window = glfwCreateWindow(WIDTH, HEIGHT, "Hello World!", NULL, NULL);
		if (window == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
        keyCall = new InputHandler();
        glfwSetKeyCallback(window, keyCall);


		// Get the resolution of the primary monitor
		ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		// Center our window
		glfwSetWindowPos(
				window,
				(GLFWvidmode.width(vidmode) - WIDTH) / 2,
				(GLFWvidmode.height(vidmode) - HEIGHT) / 2
		);

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);

		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);


    }

    public void worldSetup() {
	    renderList = new ArrayList<GenericRect>();
	    physList = new ArrayList<GenericRect>();
	    updateList = new ArrayList<GenericRect>();
        bgRenderList = new ArrayList<GenericRect>();
	    character = new MainChar("assets/char.obj", "null", new Vector3f(-22, 2, 0));
        f = new Floor("assets/floor.obj", "null", new Vector3f(0, -1, 0));
        Obstacle o = new Obstacle("assets/obstacle.obj", "null", new Vector3f(15, 1, 0), .5f, 0,0,1);
        renderList.add(o);
	    renderList.add(f);
	    renderList.add(character);
	    physList.add(character);

        physList.add(o);


    }


    public void glSetup() {

        glViewport(0, 0, WIDTH, HEIGHT);
        glClear(GL_COLOR_BUFFER_BIT);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(-30, 30, -30, 30, 100.f, -100.f);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();



    }

    public void renderText(String s) {
        BufferedImage b = TextureLoader.generate(s, 15);

        TextureLoader.renderImage(TextureLoader.loadTexture(b));

    }
    //handles rendering, dispatches render calls to all objects that need to be rendered.
    //will have multiple layers for rendering gui/score and the world, and the background
    //background will be static, and the foreground will be animated. Layering is important, as it does rendering based on layers. Topmost is called last, so GUI last
    //and bottom called first, so background first.
    public void render() {
        //back
        glPushMatrix();
        glTranslatef(0,0,1);
        for(GenericRect r:bgRenderList)r.render();
        glPopMatrix();
        //test
        //mid


	    for (GenericRect r : renderList) r.render();
	    //new Axes().render();
        //fore
        for (GenericRect r : renderList) r.renderAABB();
        if (gameOver) {
            renderText("GAME OVER, Score: " + score);
        } else {
            if (hardMode) {
                renderText("Score: " + score+" HARDMODE");
            }
            else{
                renderText("Score: "+score);
            }
        }

        //attempt to introduce jitter
        if(hardMode) {

           //rotate within extents on all Axes

            if(this.hardmodeRotations.z > 40)this.zrotLock = true;
            if(this.hardmodeRotations.z < -40 && this.zrotLock)this.zrotLock = false;
            if(this.zrotLock){
                this.hardmodeRotations.z-=.5;
            } else{
                this.hardmodeRotations.z+=.5;
            }

            if(this.hardmodeRotations.x > (50))this.xrotLock = true;
            if(this.hardmodeRotations.x < (-50) && this.xrotLock)this.xrotLock = false;
            if(this.xrotLock){
                this.hardmodeRotations.x-=.5;
            } else{
                this.hardmodeRotations.x+=.5;
            }






            //hardmodeRotations defines the current rotations per Axis.
            //when  the rotations are above 10, switch the increment to negative
            //only rotate on the Axis currently true, e.g x or y or z
            //only increment by one per each Axis per frame

            glLoadIdentity();

            //System.out.println(hardmodeRotations);
            glRotatef(this.hardmodeRotations.z, 0, 1, 0);
            glRotatef(this.hardmodeRotations.x, 1, 0, 0);




        }

    }

    //handles frame to frame logic
    public boolean coinFlip(){

        return (Math.random()>=.5f);
    }
    public void update() {
        //update hardmode status, change music
        if( hardModeoverride && !hardMode||(score>15 && !hardMode )) {
            hardMode = true;
            musicFile = hardmodeFile;
            this.musicThread.stop();
            //musicPlaying  = false;
        }
        //put more objects in
        if (!gameOver) {
            counter = (counter < 100 ? counter + 1 : 0);
            if (Math.random() * 2 + counter > 100) {
                float f = (float) (Math.random() * 1);
                if (f < .3) f += .5;

                else if (f > 2) f -= 1;
                Obstacle o = new Obstacle("assets/obstacle.obj", "null", new Vector3f(35, 1, 0), f, 0, 0,1);
                renderList.add(o);
                physList.add(o);
            }
        }

	    //Add Particle trail to player, to make it seem like you're moving
	    double randVal = Math.random() * 1;

	    while (randVal < .2) {
		    randVal = Math.random() * 1;
		    float scale = (float) (Math.random() * 2);
		    float lSpeed = (float) Math.random() * 1+.5f;
		    float varyYdir = (float) Math.random() * .5f;
		    float lifespan = (float) (Math.random() * 50);
		    Particle p = new Particle("assets/particle.obj",new Vector3f(character.pos.x, character.pos.y, 0), lifespan, scale, lSpeed, varyYdir, 0, (float) (.5f*Math.random()), .75f,1);
		    renderList.add(p);
		    updateList.add(p);
	    }
        //add Background render list


         float scale = (float) (20*Math.random()+5);
         float lSpeed = (float) Math.random() * 1 ;
         float varyYdir = (float) Math.random() * 50f;
         Particle p = new Particle("assets/bgPart.obj",new Vector3f(30, varyYdir, (float) -.1), 400+((float)Math.random()*50), scale, lSpeed, 0,1, (float) (Math.random()*.5),0, (float) (Math.random()*2));
         bgRenderList.add(p);
         updateList.add(p);





        //input
        if (InputHandler.keys[GLFW_KEY_ESCAPE]) {
            glfwSetWindowShouldClose(window, GL_TRUE);
        }
        if (InputHandler.keys[GLFW_KEY_A]) glTranslatef(-1, 0, 0);
        if(!InputHandler.keys[GLFW_KEY_E]) eKeyedge = false;
        if(InputHandler.keys[GLFW_KEY_E] && !eKeyedge){ hardModeoverride = !hardModeoverride; eKeyedge = true;}
        if (InputHandler.keys[GLFW_KEY_D]) glTranslatef(1, 0, 0);
        if(InputHandler.keys[GLFW_KEY_Q]) glRotatef(1.5f, .25f, .75f, 0);
	    //so very simple addition of speed in ydir
	    if (InputHandler.keys[GLFW_KEY_SPACE]) {
		    character.jump();

	    }

        if (InputHandler.keys[GLFW_KEY_SPACE] && gameOver) {
            gameOver = false;
            gameOversecond = false;
            this.score = 0;
            this.physList = new ArrayList<GenericRect>();
            this.physList.add(character);
            this.renderList = new ArrayList<GenericRect>();
            this.renderList.add(character);
            this.renderList.add(f);

            if(!hardModeoverride)hardMode = false;

            glLoadIdentity();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

	    //System.out.println("SPEED " + character.speed);
	    //System.out.println(character.bBox.botRight.y);
	    //logic


        //transforms


        //physics concerns go here
        if (!gameOver) {
            ArrayList<GenericRect> trash = new ArrayList<GenericRect>();
	        for (GenericRect r : updateList) {
		        if (r.getAABB().botRight.x < -30 || !r.isAlive()) {
			        trash.add(r);
		        }
		        r.update();


	        }
            for (GenericRect r : physList) {
                if (r.getAABB().botRight.x < -30) {
                    trash.add(r);
	                //System.out.println("TRASHED");
	                //if box is far enough left, delete.
	                score += 1;
                    playSFX("assets/scoreSound.wav");
                }
                r.update();
            }
            for (GenericRect r : trash) {
	            if (r.getName().equals("Particle") && !r.isAlive()) {
		            updateList.remove(r);
		            renderList.remove(r);
                    if(bgRenderList.contains(r)) bgRenderList.remove(r);
	            }
	            physList.remove(r);
                renderList.remove(r);
            }
            Physics.updatePhysics(physList);
        }
        //edge event for playing game over sound
        if(gameOver && !this.gameOversecond){
            playSFX("assets/gameover.wav");
            this.gameOversecond = true;
            glPushMatrix();
            glTranslatef(0,0,-1);
            glBegin(GL_QUADS);
            glColor3f(1,0,0);
            glVertex2f(-30, -30);
            glVertex2f(-30, 30);
            glVertex2f(30, 30);
            glVertex2f(30, -30);
            glEnd();
            glPopMatrix();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }


        //play music
        if(!this.musicThread.isAlive()) musicPlaying = false;
        if(!musicPlaying && !gameOver) {
            musicThread = new Thread(new Runnable() {
                // The wrapper thread is unnecessary, unless it blocks on the
                // Clip finishing; see comments.
                public void run() {
                    try {
                        SoundUtil.play(GameMain.musicFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (UnsupportedAudioFileException e) {
                        e.printStackTrace();
                    } catch (LineUnavailableException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            musicPlaying = true;
            musicThread.start();

        }

    }

    public static void playSFX(final String s) {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    SoundUtil.play(new File(s));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }




    public void loop() {
		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the ContextCapabilities instance and makes the OpenGL
		// bindings available for use.
		GLContext.createFromCurrent();

        glSetup();

        worldSetup();

        // Set the clear color
        glClearColor(0f, 0.0f, 0.0f, 0.0f);

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
        glEnable(GL_DEPTH_TEST);
        while (glfwWindowShouldClose(window) == GL_FALSE) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer


            //logic calls
            update();
            //rendering goes here
            render();


			glfwSwapBuffers(window); // swap the color buffers


            // Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();


        }
	}

}
