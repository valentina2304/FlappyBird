Flappy Bird - Java Game

Flappy Bird is a classic game recreated in Java. The goal of the game is to navigate the bird through obstacles without colliding with them, accumulating as many points as possible.

Technologies Used
1. Java
The game is implemented in Java, using IntelliJ IDEA. Java's portability and performance make it ideal for small to medium-sized 2D games.

2. Java Swing
Swing is used for the GUI, providing flexibility in rendering and user interaction. Key components:

JPanel – for game rendering (paintComponent(Graphics g))

Timer – for animations and game updates

KeyListener – for handling player input

3. Java AWT
AWT enhances graphical rendering with:

Graphics2D – for smooth drawing and anti-aliasing

AffineTransform – for dynamic bird rotation

Image – for textures (background, bird, obstacles)

Color & Font – for UI customization (score display, messages)

4. Java Sound API
Used for sound effects (jump, collision, scoring). It manages audio playback via:

AudioSystem, Clip, AudioInputStream – for loading and playing sounds



How to Run the Project

Clone the repository:
git clone https://github.com/user/FlappyBird-Game.git

Compile and run the game:
cd FlappyBird-Game
javac src/*.java -d bin
java -cp bin Main
