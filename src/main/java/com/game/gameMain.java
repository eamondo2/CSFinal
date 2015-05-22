
package com.game;


//imports

import com.game.math.Vector3f;
import com.game.structure.floor;
import com.game.structure.mainchar;
import com.game.structure.obstacle;
import com.game.structure.rect;
import com.game.util.Physics;
import com.game.util.TextureLoader;
import com.game.util.inputHandler;
import org.lwjgl.Sys;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import java.awt.image.BufferedImage;
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


public class gameMain {
    public static boolean gameOver = false;
    public floor f;
    public int score = 0;
    public int counter = 0;
    public ArrayList<rect> renderList;
	public ArrayList<rect> physList;
	public mainchar character;
	int WIDTH = 700;
    int HEIGHT = 700;
    //Where to begin?
	//beginnings of lwjgl implementation.
	private GLFWErrorCallback errorCall;
    private float rot = 0;
    private inputHandler keyCall;
    private long window;


	public static void main(String[] args) {
		System.out.println("Hello World!");
        System.out.println(GL_LINE);


        new gameMain().run();


	}

	public void run() {
		System.out.println("HI THERE " + Sys.getVersion());


		try {
			init();
			loop();

			glfwDestroyWindow(window);
			keyCall.release();
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
        keyCall = new inputHandler();
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
	    renderList = new ArrayList<rect>();
	    physList = new ArrayList<rect>();
        character = new mainchar("assets/char.obj", "null", new Vector3f(-15, 2, 0));
        f = new floor("assets/floor.obj", "null", new Vector3f(0, -1, 0));
        obstacle o = new obstacle("assets/obstacle.obj", "null", new Vector3f(15, 1, 0), .5f, 1);
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
        glOrtho(-20, 20, -20, 20, 10.f, -10.f);

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
        //test
        //mid


	    for (rect r : renderList) r.render();
	    //new axes().render();
        //fore
        for (rect r : renderList) r.renderAABB();
        if (gameOver) {
            renderText("GAME OVER, score: " + score);
        } else {
            renderText("score: " + score);
        }

    }

    //handles frame to frame logic
    public void update() {
        //put more objects in
        if (!gameOver) {
            counter = (counter < 100 ? counter + 1 : 0);
            if (Math.random() * 2 + counter > 100) {
                float f = (float) (Math.random() * 1);
                if (f < .3) f += .5;
                else if (f > 2) f -= 1;
                obstacle o = new obstacle("assets/obstacle.obj", "null", new Vector3f(20, 1, 0), f, 1);
                renderList.add(o);
                physList.add(o);
            }
        }



        //input
        if (inputHandler.keys[GLFW_KEY_ESCAPE]) {
            glfwSetWindowShouldClose(window, GL_TRUE);
            System.out.println("ShouldClose");
        }
        if (inputHandler.keys[GLFW_KEY_A]) glTranslatef(-1, 0, 0);
        if (inputHandler.keys[GLFW_KEY_D]) glTranslatef(1, 0, 0);
        if(inputHandler.keys[GLFW_KEY_Q]) glRotatef(1.5f, .25f, .75f, 0);
	    //so very simple addition of speed in ydir
	    if (inputHandler.keys[GLFW_KEY_SPACE]) {
		    character.jump();
	    }
        if (inputHandler.keys[GLFW_KEY_SPACE] && gameOver) {
            gameOver = false;
            this.score = 0;
            this.physList = new ArrayList<rect>();
            this.physList.add(character);
            this.renderList = new ArrayList<rect>();
            this.renderList.add(character);
            this.renderList.add(f);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("SPEED " + character.speed);
        System.out.println(character.bBox.botRight.y);
	    //logic


        //transforms


        //physics concerns go here
        if (!gameOver) {
            ArrayList<rect> trash = new ArrayList<rect>();
            for (rect r : physList) {
                if (r.getAABB().botRight.x < -30) {
                    trash.add(r);
                    System.out.println("TRASHED");
                    score += 1;
                }
                r.update();
            }
            for (rect r : trash) {
                physList.remove(r);
                renderList.remove(r);
            }
            Physics.updatePhysics(physList);
        }

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
