package dk.sdu.mmmi.cbse.common.data;

import java.io.Serializable;
import java.util.UUID;

public final class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    private EntityType type;
    private float x;
    private float y;
    private float dx;
    private float dy;
    private float radians;
    private float maxSpeed;
    private float acceleration;
    private float deacceleration;
    private float[] shapeX;
    private float[] shapeY;
    private int rotationSpeed;
    private float[] dist;
    private int numPoints;
    private int width;
    private int height;
    private float lifeTime;
    private float lifeTimer;
    private boolean remove;
    private EntityType sizeType;
    private boolean hit;
    private boolean shoot;
    private String ownerID = "";

    public boolean isRemoved() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }
    
    public boolean isShoothing() {
        return shoot;
    }
    
    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public void setSizeType(EntityType size) {
        this.sizeType = size;
    }

    public EntityType getSizeType() {
        return this.sizeType;
    }

    public float getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(float lifeTime) {
        this.lifeTime = lifeTime;
    }

    public float getLifeTimer() {
        return lifeTimer;
    }

    public void setLifeTimer(float lifeTimer) {
        this.lifeTimer = lifeTimer;
    }

    public String getID() {
        return ID.toString();
    }

    public void setType(EntityType type) {
        this.type = type;
    }

    public EntityType getType() {
        return type;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getRadians() {
        return radians;
    }

    public void setRadians(float radians) {
        this.radians = radians;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public float getDeacceleration() {
        return deacceleration;
    }

    public void setDeacceleration(float deacceleration) {
        this.deacceleration = deacceleration;
    }

    public float[] getShapeX() {
        return shapeX;
    }

    public void setShapeX(float[] shapeX) {
        this.shapeX = shapeX;
    }

    public float[] getShapeY() {
        return shapeY;
    }

    public void setShapeY(float[] shapeY) {
        this.shapeY = shapeY;
    }

    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public void setDist(float[] dist) {
        this.dist = dist;
    }

    public float[] getDist() {
        return dist;
    }

    public int getNumPoints() {
        return numPoints;
    }

    public void setNumPoints(int numpoints) {
        numPoints = numpoints;
    }

    public void setRect(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
