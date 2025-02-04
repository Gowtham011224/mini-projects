# Importing the pygame module
import pygame
from pygame.locals import *

# Initiate pygame and give permission
# to use pygame's functionality
pygame.init()

# Create a display surface object
# of specific dimension
window = pygame.display.set_mode((600, 600))


# Create a list of different sprites
# that you want to use in the animation
image_sprite = [pygame.image.load("sprite1.jpg"),pygame.image.load("sprite2.jpg"),
                pygame.image.load("sprite3.jpg"),pygame.image.load("sprite4.jpg")]


# Creating a new clock object to
# track the amount of time
clock = pygame.time.Clock()

# Creating a new variable
# We will use this variable to
# iterate over the sprite list
value = 0

# Creating a boolean variable that
# we will use to run the while loop
run = True

# Creating a boolean variable to
# check if the character is moving
# or not
moving = False

# Creating a variable to store
# the velocity
velocity = 12

# Starting coordinates of the sprite
x = 100
y = 150

# Creating an infinite loop
# to run our game
while run:

	# Setting the framerate to 7fps just
	# to see the result properly
	clock.tick(7)

	# iterate over the list of Event objects
	# that was returned by pygame.event.get() method.
	for event in pygame.event.get():

		# Closing the window and program if the
		# type of the event is QUIT
		if event.type == pygame.QUIT:
			pygame.quit()
			quit()

		# Checking event key if the type
		# of the event is KEYUP or KEYDOWN i.e.
		# keyboard button is released
		if event.type == pygame.KEYUP or event.type == pygame.KEYDOWN :

			# Setting the value of moving to False
			# and the value f value variable to 0
			# if the button released is
			# Left arrow or right arrow or up arrow or down arrow key
			if event.key == pygame.K_LEFT or event.key == pygame.K_RIGHT or event.key == pygame.K_UP or event.key == pygame.K_DOWN :
				moving = False
				
	# Storing the key pressed in a
	# new variable using key.get_pressed()
	# method
	key_pressed_is = pygame.key.get_pressed()

	# Changing the x coordinate
                # changing the y coordinates
	# of the player and setting moving
	# variable to True
	if key_pressed_is[K_LEFT]:
		x -= 8
		moving = True
	if key_pressed_is[K_RIGHT]:
		x += 8
		moving = True
	if key_pressed_is[K_UP]:
		y -= 8
		moving = True
	if key_pressed_is[K_DOWN]:
		y += 8
		moving = True

		
	# If moving variable is True
	# then increasing the value of
	# value variable by 1
	if moving:
		value += 1

	# Setting 0 in value variable if its
	# value is greater than the length
	# of our sprite list
	if value >= len(image_sprite):
		value = 0

                # In a variable named image
	# We storing the sprite image in an
	image = image_sprite[value]

	# Scaling the image
	image = pygame.transform.scale(image, (180, 180))

	# Displaying the image in our game window
	window.blit(image, (x, y)) 

	# Updating the frames in display surface
	pygame.display.update()

	# Filling the window with white color
                # color formate(R,G,B)
	window.fill((255,255,255))