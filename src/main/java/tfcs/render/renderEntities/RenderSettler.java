package tfcs.render.renderEntities;

import java.util.UUID;

import org.lwjgl.opengl.GL11;

import com.bioxx.tfc.Entities.Mobs.EntitySkeletonTFC;
import com.mojang.authlib.GameProfile;

import tfcs.entity.EntitySettler;
import tfcs.reference.ReferenceResource;
import tfcs.render.models.ModelSettler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;

public class RenderSettler extends RenderBiped {

	public RenderSettler() {
		super(new ModelSettler(), 0.5F);
	}

	@Override
	protected void renderEquippedItems(EntityLiving entity, float f) {
		super.renderEquippedItems(entity, f);
		EntitySettler es = (EntitySettler) entity;
		Item item;
		ItemStack itemstack = es.getShield();
		if (itemstack != null && itemstack.getItem() != null) {
			item = itemstack.getItem();
			GL11.glPushMatrix();
			float f1;
			this.modelBipedMain.bipedLeftArm.postRender(0.0625F);
			GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);

			if (item.isFull3D()) {
				f1 = 0.625F;

				if (item.shouldRotateAroundWhenRendering()) {
					GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
					GL11.glTranslatef(0.0F, -1.0F, 0.0F);
				}

				this.func_82422_c();
				GL11.glScalef(f1, -f1, f1);
				GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(41.0F, 0.0F, 1.0F, 0.0F);
				GL11.glTranslatef(0.65F, -0.45F, 0.0F);
			} else {
				
				f1 = 0.375F;
				GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
				GL11.glScalef(f1, f1, f1);
				GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
			}

			float f2;
			int i;
			float f5;

			i = itemstack.getItem().getColorFromItemStack(itemstack, 0);
			float f4 = (float) (i >> 16 & 255) / 255.0F;
			f5 = (float) (i >> 8 & 255) / 255.0F;
			f2 = (float) (i & 255) / 255.0F;
			GL11.glColor4f(f4, f5, f2, 1.0F);
			this.renderManager.itemRenderer.renderItem(entity, itemstack, 0);
			GL11.glPopMatrix();
		}
	}

	protected void scaleRender(EntitySettler settler, float par2) {
	}

	// translateRenderer
	@Override
	protected void func_82422_c() {
	}

	protected ResourceLocation getTextureLocation(EntitySettler entitySettler) {
		return ReferenceResource.SETTLER_SKIN_TEXTURE;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLiving entity) {
		return this.getTextureLocation((EntitySettler) entity);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLivingBase, float f) {
		this.scaleRender((EntitySettler) entityLivingBase, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getTextureLocation((EntitySettler) entity);
	}
}
