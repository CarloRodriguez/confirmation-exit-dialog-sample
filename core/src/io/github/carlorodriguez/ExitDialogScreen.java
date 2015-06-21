package io.github.carlorodriguez;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class ExitDialogScreen implements Screen, InputProcessor {

    private ExitDialogGame game;
    private static boolean showingExitDialog;
    public Stage stage;

    public ExitDialogScreen(final ExitDialogGame game) {
        this.game = game;
    }

    public void showExitDialog(Stage stage) {
        TextureAtlas atlas;
        atlas = new TextureAtlas(Gdx.files.internal("dialog.atlas"));
        Skin skin = new Skin();
        skin.addRegions(atlas);

        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.titleFont = game.font;
        NinePatch background = skin.getPatch("background");
        windowStyle.background = new NinePatchDrawable(background);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = skin.getDrawable("button");
        style.down = skin.getDrawable("touched-button");
        style.font = game.font;

        Label.LabelStyle LabelStyle = new Label.LabelStyle(game.font,
                Color.WHITE);
        Label label = new Label("Do you really want to leave?", LabelStyle);
        label.setAlignment(Align.center);

        Dialog dialog = new Dialog("", windowStyle) {

            @Override
            protected void result(Object object) {
                boolean exit = (Boolean) object;
                if (exit) {
                    Gdx.app.exit();
                } else {
                    remove();
                }
                showingExitDialog = false;
            }

            @Override
            public Dialog show(Stage stage) {
                showingExitDialog = true;
                return super.show(stage);
            }

            @Override
            public void cancel() {
                showingExitDialog = false;
                super.cancel();
            }

            @Override
            public float getPrefHeight() {
                return 200f;
            }
        };
        dialog.button("Yes", true, style);
        dialog.button("No", false, style);
        dialog.key(Input.Keys.ENTER, true);
        dialog.key(Input.Keys.ESCAPE, false);
        dialog.getContentTable().add(label);
        if (!showingExitDialog)
            dialog.show(stage);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        stage.act(delta);
        stage.draw();

        game.batch.end();
    }

    @Override
    public void show() {
        Gdx.input.setCatchMenuKey(true);
        Gdx.input.setCatchBackKey(true);
        stage = new Stage(new StretchViewport(800, 480));
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void hide() {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        showExitDialog(stage);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
