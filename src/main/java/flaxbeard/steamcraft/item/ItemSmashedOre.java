package flaxbeard.steamcraft.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.commons.lang3.tuple.MutablePair;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.steamcraft.SteamcraftBlocks;
import flaxbeard.steamcraft.SteamcraftItems;



public class ItemSmashedOre extends Item {
	
	
	public IIcon theOverlay;
	public static ArrayList<MutablePair<String,MutablePair<IIcon,String>>> oreTypes = new ArrayList<MutablePair<String,MutablePair<IIcon,String>>>();
	public static HashMap<String,Integer> oreTypesFromOre = new HashMap<String,Integer>();
	private static int id = 0;
	
	public ItemSmashedOre(){
		super();
		this.setHasSubtypes(true);
		
		oreTypes.add(getPair("oreIron", null, "Iron"));
		oreTypes.add(getPair("oreGold", null, "Gold"));
		oreTypes.add(getPair("oreCopper", null, "Copper"));
		oreTypes.add(getPair("oreZinc", null, "Zinc"));
		oreTypes.add(getPair("oreTin", null, "Tin"));
		oreTypes.add(getPair("oreNickel", null, "Nickel"));
		oreTypes.add(getPair("oreSilver", null, "Silver"));
		oreTypes.add(getPair("oreLead", null, "Lead"));
		oreTypes.add(getPair("oreAluminum", null, "Aluminum"));
		oreTypes.add(getPair("oreOsmium", null, "Osmium"));
		oreTypes.add(getPair("oreCobalt", null, "Cobalt"));
		oreTypes.add(getPair("oreArdite", null, "Ardite"));
		oreTypes.add(getPair("oreCinnabar", null, "Cinnabar"));

		
	}
	
	private MutablePair<String, MutablePair<IIcon,String>> getPair(String oreDict, IIcon icon, String uName){
		oreTypesFromOre.put(oreDict, id);
		id++;
		if (OreDictionary.getOres(oreDict).size() > 0) {
			ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(OreDictionary.getOres(oreDict).get(0));
			System.out.println(result == null ? "NO RESULT" : result.toString());

			GameRegistry.addSmelting(new ItemStack(SteamcraftItems.smashedOre,1,id), result, 0.5F);
		}
		return new MutablePair<String, MutablePair<IIcon, String>>(oreDict, new MutablePair<IIcon, String>(icon, uName));
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tabs, List list) {
		
		for (int i = 0; i < oreTypes.size(); i++){
			if (OreDictionary.getOres(oreTypes.get(i).getLeft()).size() > 0){
				list.add(new ItemStack(item, 1, i));
			}
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return super.getUnlocalizedName() + "." + oreTypes.get(stack.getItemDamage()).getRight().getRight();
	}
	
	
	@SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return false;
        
    }
	
	
	
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
		return oreTypes.get(meta).getRight().getLeft();
        
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register)
    {		
		for (int i = 0; i < oreTypes.size(); i++){
			oreTypes.get(i)
					.getRight()
					.setLeft(
					   register.registerIcon(
						 this.getIconString() + oreTypes.get(i).getRight().getRight()
					   )
					);
		}
        
    }
	
	
	
	
	
	

}