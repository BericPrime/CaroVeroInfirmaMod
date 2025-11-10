package net.dengusprime.caroveroinfirma.block.autodoc;

import net.dengusprime.caroveroinfirma.block.entity.AutodocXIBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class AutodocXIBlock extends HorizontalDirectionalBlock implements EntityBlock {
    public static final EnumProperty<Part> PART = EnumProperty.create("part", Part.class);
    public static final BooleanProperty OCCUPIED = BooleanProperty.create("occupied");

    public AutodocXIBlock(Properties props) {
        super(props.noOcclusion());
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(PART, Part.FOOT)
                .setValue(OCCUPIED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART, OCCUPIED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Direction dir = ctx.getHorizontalDirection();
        BlockPos headPos = ctx.getClickedPos().relative(dir);
        Level level = ctx.getLevel();
        if (level.getBlockState(headPos).canBeReplaced(ctx)) {
            return this.defaultBlockState().setValue(FACING, dir);
        }
        return null;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        Direction dir = state.getValue(FACING);
        BlockPos headPos = pos.relative(dir);
        level.setBlock(headPos, state.setValue(PART, Part.HEAD), 3);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moving) {
        if (state.getBlock() != newState.getBlock()) {
            Direction dir = state.getValue(FACING);
            BlockPos otherPos = (state.getValue(PART) == Part.FOOT) ? pos.relative(dir) : pos.relative(dir.getOpposite());
            BlockState otherState = level.getBlockState(otherPos);
            if (otherState.getBlock() == this) {
                level.removeBlock(otherPos, false);
            }
        }
        super.onRemove(state, level, pos, newState, moving);
    }

    public enum Part implements StringRepresentable {
        HEAD("head"), FOOT("foot");
        private final String name;
        Part(String name) { this.name = name; }
        public String getSerializedName() { return name; }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new net.dengusprime.caroveroinfirma.block.entity.AutodocXIBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof AutodocXIBlockEntity autodoc) {
                NetworkHooks.openScreen((ServerPlayer) player, autodoc, pos);
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }
}