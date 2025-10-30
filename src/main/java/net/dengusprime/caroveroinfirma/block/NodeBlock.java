package net.dengusprime.caroveroinfirma.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

//basically c+v amethyst block lol
public class NodeBlock extends AmethystClusterBlock {
    private static final int pSize = 8;
    private static final int pOffset = 8;

    //collision shapes
    protected static final VoxelShape NORTH_AABB = Block.box(4, 4, 8, 12, 12, 16);
    protected static final VoxelShape SOUTH_AABB = Block.box(4, 4, 0, 12, 12, 8);
    protected static final VoxelShape EAST_AABB = Block.box(0, 4, 4, 8, 12, 12);
    protected static final VoxelShape WEST_AABB = Block.box(8, 4, 4, 16, 12, 12);
    protected static final VoxelShape UP_AABB = Block.box(4, 0, 4, 12, 8, 12);
    protected static final VoxelShape DOWN_AABB = Block.box(4, 8, 4, 12, 16, 12);
    public NodeBlock(Properties pProperties) {
        super(pSize, pOffset, pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch (direction) {
            case NORTH:
                return NORTH_AABB;
            case SOUTH:
                return SOUTH_AABB;
            case EAST:
                return EAST_AABB;
            case WEST:
                return WEST_AABB;
            case DOWN:
                return DOWN_AABB;
            default:
            case UP:
                return UP_AABB;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return this.getShape(pState, pLevel, pPos, pContext);
    }


}
