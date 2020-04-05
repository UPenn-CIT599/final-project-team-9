/**
 * Abstract class to create general brick
 * Criteria for specific brick must be color, hits to break, size, and position 
 * @author jacob, muizz, raheel 
 */
public abstract class Bricks {
    
    //Note: how-to create visible boolean built into abstract class still in research 
    private boolean visible = true;
    
    public abstract void hitToBreak();
    public abstract void color();
    public abstract void size();
    public abstract void position();
    
}

/**
 * This redbrick class will be for the easiest brick, red brick 
 * @author jacob, muizz, raheel 
 */
class RedBrick extends Bricks{
    
    @Override
    public void hitToBreak() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void color() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void size() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void position() {
        // TODO Auto-generated method stub
        
    }
    
}

/**
 * This greenbrick class will be for the second easiest brick, green brick 
 * @author jacob, muizz, raheel 
 */
class GreenBrick extends Bricks{

    @Override
    public void hitToBreak() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void color() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void size() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void position() {
        // TODO Auto-generated method stub
        
    }
    
}

/**
 * This bluebrick class will be for the medium brick, blue brick 
 * @author jacob, muizz, raheel 
 */
class BlueBrick extends Bricks{

    @Override
    public void hitToBreak() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void color() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void size() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void position() {
        // TODO Auto-generated method stub
        
    }
    
}

/**
 * This orangebrick class will be for the second most difficult brick, orange brick 
 * @author jacob, muizz, raheel 
 */
class OrangeBrick extends Bricks{

    @Override
    public void hitToBreak() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void color() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void size() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void position() {
        // TODO Auto-generated method stub
        
    }
    
}

/**
 * This graybrick class will be for the most difficult brick, gray brick 
 * @author jacob, muizz, raheel 
 */
class GrayBrick extends Bricks{

    @Override
    public void hitToBreak() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void color() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void size() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void position() {
        // TODO Auto-generated method stub
        
    }
    
}

