# CSFinal
Adam, Zach, Eamon. Attempt to make a simple game.

This needs to be the most simple game ever created. I will attempt to find a collision engine we can use, if not, then I'll make something. - Eamon

The real question is whether or not the game will be 3d or not. At the moment, I'm leaning towards not, as it will simplify things a whole lot.

2d collision is not as hard as 3d, and rendering gets easier.

Current things to work on: Rendering engine - entirety Making models - game characters etc. not sure if 3d or not at the moment. Game art - will have to wait for making the rendering engine, and determining actual content.

For this entire project, all things will be modular. The characters and objects etc will all be children of master interface classes, so that everything will work together noicely.

What will the game even be about? Or will it just be a tech demo, of a collision engine, maybe a jumping puzzle?

As far as the meshes for objects goes, the points will be stored in an array of Point objects, in a CCW direction. This will be the standard for all things.

Meshes will be loaded from files, not code defined. Meshes will all be points around the orgin, which will be shifted to their desired location.

Progress has been made, I have attempted to add Drone.io build support, but it doesn't seem to be working. Maven doesn't want to not be a bastard.
