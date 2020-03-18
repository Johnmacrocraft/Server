package cn.nukkit.level.generator.standard.misc;

import cn.nukkit.level.generator.standard.StandardGenerator;
import cn.nukkit.level.generator.standard.StandardGeneratorUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Base class for all implementations of {@link GenerationPass}.
 * <p>
 * Allows the user to override the generation of the local seed in order to ensure consistent generation even if other generation passes are added, modified
 * or removed.
 *
 * @author DaPorkchop_
 */
@JsonDeserialize
public abstract class AbstractGenerationPass implements GenerationPass {
    @JsonProperty
    private long seed = -1L;

    @Override
    public final void init(long levelSeed, long localSeed, StandardGenerator generator) {
        this.init0(levelSeed, this.seed == -1L ? localSeed : levelSeed ^ this.seed, generator);
    }

    protected void init0(long levelSeed, long localSeed, StandardGenerator generator) {
        //no-op
    }

    @JsonSetter("seed")
    protected void setSeed(String seed) {
        long theSeed = StandardGeneratorUtils.hash(seed);
        this.seed = theSeed == -1L ? 0L : theSeed;
    }
}