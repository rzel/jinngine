package jinngine.game.actors.button;


import java.io.IOException;
import org.newdawn.slick.openal.*;

import com.ardor3d.image.Texture;
import com.ardor3d.image.TextureStoreFormat;
import com.ardor3d.math.Vector2;
import com.ardor3d.renderer.state.TextureState;
import com.ardor3d.renderer.state.RenderState.StateType;
import com.ardor3d.scenegraph.Node;
import com.ardor3d.util.TextureManager;
import com.ardor3d.util.resource.ResourceLocatorTool;

import jinngine.game.Game;
import jinngine.game.actors.ActionActor;
import jinngine.game.actors.Actor;
import jinngine.game.actors.ActorOwner;
import jinngine.game.actors.ScalableActor;
import jinngine.game.actors.interaction.DeleteActor;
import jinngine.game.actors.interaction.ScaleActor;


public class ScaleActorButton extends Button {
	
	private Texture selectedtexture;
	private Texture deselectedtexture;	
	private TextureState texturestate;
	private Audio click;
	
	public ScaleActorButton() {
		try {
			click = SoundStore.get().getWAV(
					ResourceLocatorTool.locateResource(
							ResourceLocatorTool.TYPE_AUDIO, "audiodump.wav").openStream());

			System.out.println(click);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void act(Game game) {
		super.act(game);
	}
	
	@Override
	public void start(Game game) {
		super.start(game);
		
		// load textures
		selectedtexture = TextureManager.load("selectedscaleactor.tga",
				Texture.MinificationFilter.Trilinear,
				TextureStoreFormat.GuessNoCompressedFormat, true);

		deselectedtexture = TextureManager.load("deselectedscaleactor.tga",
				Texture.MinificationFilter.Trilinear,
				TextureStoreFormat.GuessNoCompressedFormat, true);
		
		// get the texture state on the button box
		texturestate = (TextureState)buttonnode.getChild("mybuttonbox").getLocalRenderState(StateType.Texture);

		// set the deselected texture by default
		texturestate.setTexture(deselectedtexture);	
	}
	
	@Override
	public ActionActor provideActionActor(ActorOwner owner, Actor target, Node picknode,
			jinngine.math.Vector3 pickpoint, Vector2 screenpos ) {
		
		if (target instanceof ScalableActor ) {
		// spawn delete actor
			return new ScaleActor(owner, (ScalableActor)target);
		} else {
			return null;
		}
	}
	
	@Override
	public void setSelected(Game game, boolean selected) {
		super.setSelected(game, selected);
		
		if (selected) {
			// play click sound
			click.playAsSoundEffect(1, 100, false);

			texturestate.setTexture(selectedtexture);
						
			
		} else {
			click.playAsSoundEffect(1, 100, false);

			// play click sound
			texturestate.setTexture(deselectedtexture);	
		}
	}
	
	@Override
	public boolean canBeSelected() {
		return true;
	}
	
}