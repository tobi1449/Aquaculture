package rebelkeithy.mods.aquaculture.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTackleBox extends ModelBase {
	// fields
	ModelRenderer handleright;
	ModelRenderer handleleft;
	ModelRenderer handletop;
	ModelRenderer tackleboxbottom;
	ModelRenderer tackleboxtop;

	public ModelTackleBox() {
		textureWidth = 64;
		textureHeight = 32;

		handleright = new ModelRenderer(this, 0, 18);
		handleright.addBox(0F, 0F, 0F, 1, 1, 1);
		handleright.setRotationPoint(1F, 17F, 0F);
		handleright.setTextureSize(64, 32);
		handleright.mirror = true;
		setRotation(handleright, 0F, 0F, 0F);
		handleleft = new ModelRenderer(this, 0, 16);
		handleleft.addBox(0F, 0F, 0F, 1, 1, 1);
		handleleft.setRotationPoint(-2F, 17F, 0F);
		handleleft.setTextureSize(64, 32);
		handleleft.mirror = true;
		setRotation(handleleft, 0F, 0F, 0F);
		handletop = new ModelRenderer(this, 0, 14);
		handletop.addBox(0F, 0F, 0F, 4, 1, 1);
		handletop.setRotationPoint(-2F, 16F, 0F);
		handletop.setTextureSize(64, 32);
		handletop.mirror = true;
		setRotation(handletop, 0F, 0F, 0F);
		tackleboxbottom = new ModelRenderer(this, 0, 0);
		tackleboxbottom.addBox(-6F, -2F, -2F, 12, 4, 8);
		tackleboxbottom.setRotationPoint(0F, 22F, -2F);
		tackleboxbottom.setTextureSize(64, 32);
		tackleboxbottom.mirror = true;
		setRotation(tackleboxbottom, 0F, 0F, 0F);
		tackleboxtop = new ModelRenderer(this, 0, 22);
		tackleboxtop.addBox(-6F, -2F, -8F, 12, 2, 8);
		tackleboxtop.setRotationPoint(0F, 20F, 4F);
		tackleboxtop.setTextureSize(64, 32);
		tackleboxtop.mirror = true;
		setRotation(tackleboxtop, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	}
	
	public void render(){
		handleright.render(0.0625F);
		handleleft.render(0.0625F);
		handletop.render(0.0625F);
		tackleboxbottom.render(0.0625F);
		tackleboxtop.render(0.0625F);
	}

}
