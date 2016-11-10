package me.jjm_223.smartgiants.entities.v1_8_R3.nms;

import me.jjm_223.smartgiants.api.entity.ISmartGiant;
import me.jjm_223.smartgiants.api.util.Configuration;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;

/**
 * Created by Jacob on 3/14/2015.
 * Class for intelligent giant. For MC 1.8.3
 */
public class SmartGiant extends EntityGiantZombie implements ISmartGiant
{
    public SmartGiant(World world)
    {
        super(world);

        double health = Configuration.getInstance().maxHealth();

        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(health);
        setHealth((float) health);

        if (this instanceof SmartGiantHostile)
        {
            return;
        }

        width = 1;

        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalTempt(this, .9F, Items.APPLE, false));
        this.goalSelector.a(2, new PathfinderGoalRandomStroll(this, .9F));
        this.goalSelector.a(3, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 16.0F));
        this.goalSelector.a(4, new PathfinderGoalRandomLookaround(this));
    }

    @Override
    public float a(BlockPosition position)
    {
        return 0.5F - world.o(position);
    }

    public boolean isHostile()
    {
        return (this instanceof SmartGiantHostile);
    }
}