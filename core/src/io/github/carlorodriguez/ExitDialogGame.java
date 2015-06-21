package io.github.carlorodriguez;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ExitDialogGame extends Game {

	SpriteBatch batch;
    public BitmapFont font;

    @Override
	public void create () {
		batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"),
                Gdx.files.internal("fonts/font.png"), false);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        setScreen(new ExitDialogScreen(this));
	}

	@Override
	public void render () {
        super.render();
	}

    @Override
    public void dispose() {
        batch.dispose();
    }
}
