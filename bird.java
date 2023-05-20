import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class bird here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class bird extends Actor
{
    private double g = 1; 
    private int y = 300;
    private boolean haspressed = false;
    private boolean isalive = true;
    private boolean isacross = false;
    private boolean hasaddscore = false;
    public bird(){
        GreenfootImage image = getImage();
        image.scale(50,40);
    }
    public void act()
    {
        if(spacePressed()){
            g=-2;
        }
        g +=0.1;
        y += g;
        setLocation(getX(), (int)(y));
        if(isTouchpipe()){
            isalive = false;
        }
        
        if(isAtEdge()){
            isalive = false;
        }
        
        if(!isalive){
            getWorld().addObject(new gameover(), 300, 200);
            getWorld().removeObject(this);
        }

        if(!hasaddscore && isacross && isalive){
            Greenfoot.playSound("point.mp3");
            score.add(1);
        }
        hasaddscore = isacross;
    }
    
    public boolean spacePressed(){
        boolean pressed = false;
        if(Greenfoot.isKeyDown("space")){
            if(!haspressed){
                pressed = true;
            }
            haspressed = true;
        }else{
            haspressed = false;
        }
        return pressed;
    }
    
    public boolean isTouchpipe(){
        isacross = false;
        for(pipe pipe : getWorld().getObjects(pipe.class)){
            if(Math.abs(pipe.getX() - getX()) < 60 ){
                if(Math.abs(pipe.getY() + 30 - getY()) > 37){
                    isalive = false;
                }
                isacross = true;
            }
        }
        return !isalive;
    }
}
