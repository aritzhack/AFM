package afm.world;

import afm.blocks.Blocks;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

public class WorldGenerator implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

        int times = random.nextInt(4);

        for (int x = 0; x < times; x++) {

            int coordX = chunkX * 16 + random.nextInt(16), coordY = random.nextInt(60), coordZ = chunkZ * 16 + random.nextInt(16);

            new WorldGenMinable(Blocks.oreAFM.blockID, 20).generate(world, random, coordX, coordY, coordZ);
        }
    }
}