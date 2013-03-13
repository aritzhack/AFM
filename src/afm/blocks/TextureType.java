package afm.blocks;

public enum TextureType {
	ONE_TEX, // All sides have same texture
	TOP_BOTT_SIDES, // Different textures for bottom & top, same for all other sides
	SIX_TEX, // A different texture for each side
	DEFAULT, // Registers default texture (not recommended, uses MC's paths), and uses getBlockTextureFromSideAndMetadata
	CUSTOM, // When registerCustomIcons() and getCustomIcon() are overriden, use this
	NONE // When not wanna register, but wanna use getBlockTextureFromSideAndMetadata
}
