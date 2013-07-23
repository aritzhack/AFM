package aritzh.afm.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import aritzh.afm.blocks.Blocks;
import cpw.mods.fml.common.IWorldGenerator;

/**
 * WorldGenerator
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WorldGenerator implements IWorldGenerator {

    @Override
    public void generate(final Random random, final int chunkX, final int chunkZ, final World world, final IChunkProvider chunkGenerator, final IChunkProvider chunkProvider) {

        final int times = random.nextInt(4);

        for (int x = 0; x < times; x++) {

            final int coordX = chunkX * 16 + random.nextInt(16), coordY = random.nextInt(60), coordZ = chunkZ * 16 + random.nextInt(16);

            new WorldGenMinable(Blocks.oreAFM.blockID, 20).generate(world, random, coordX, coordY, coordZ);
        }
    }
}