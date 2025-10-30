package net.dengusprime.caroveroinfirma.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class DeadGrassBlock extends Block {
    public static final BooleanProperty ASHY = BooleanProperty.create("ashy");
    public DeadGrassBlock(Properties p_53685_) {
        super(p_53685_);
        this.registerDefaultState(this.stateDefinition.any().setValue(ASHY, false));
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ASHY);
    }
}
