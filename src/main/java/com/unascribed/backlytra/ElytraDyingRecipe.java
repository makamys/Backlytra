/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 *
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 *
 * File Created @ [21/03/2016, 00:05:19 (GMT)]
 */
package com.unascribed.backlytra;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ElytraDyingRecipe implements IRecipe {

	@Override
	public boolean matches(InventoryCrafting var1, World var2) {
		boolean foundSource = false;
		boolean foundTarget = false;

		for (int i = 0; i < var1.getSizeInventory(); i++) {
			ItemStack stack = var1.getStackInSlot(i);
			if (stack != null) {
				if (stack.getItem() instanceof ItemElytra) {
					if (foundTarget)
						return false;
					foundTarget = true;
				} else if (stack.getItem() instanceof ItemDye) {
					if (foundSource)
						return false;
					foundSource = true;
				} else
					return false;
			}
		}

		return foundSource && foundTarget;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting var1) {
		int source = -1;
		ItemStack target = null;

		for (int i = 0; i < var1.getSizeInventory(); i++) {
			ItemStack stack = var1.getStackInSlot(i);
			if (stack != null) {
				if (stack.getItem() instanceof ItemDye)
					source = stack.getItemDamage();
				else
					target = stack;
			}
		}
		if (target == null)
			return null;
		ItemStack copy = target.copy();
		if (!copy.hasTagCompound())
			copy.setTagCompound(new NBTTagCompound());
		copy.getTagCompound().setInteger("backlytra:elytraDye", source);
		return copy;
	}

	@Override
	public int getRecipeSize() {
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return null;
	}

}