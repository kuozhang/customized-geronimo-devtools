/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.geronimo.testsuite.v21.ui;

import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

import abbot.swt.finder.WidgetFinder;
import abbot.swt.finder.WidgetFinderImpl;
import abbot.swt.finder.generic.MultipleFoundException;
import abbot.swt.finder.generic.NotFoundException;
import abbot.swt.finder.matchers.IndexedMultiMatcher;
import abbot.swt.finder.matchers.TextMultiMatcher;
import abbot.swt.finder.matchers.WidgetClassMatcher;
import abbot.swt.finder.matchers.WidgetTextMatcher;
import abbot.swt.tester.ButtonTester;
import abbot.swt.tester.ItemPath;
import abbot.swt.tester.ItemTester;
import abbot.swt.tester.MenuTester;
import abbot.swt.tester.ShellTester;
import abbot.swt.tester.TextTester;
import abbot.swt.tester.TreeItemTester;
import abbot.swt.tester.TreeTester;

/**
 * @version $Rev$ $Date$
 */
public class AbbotHelper {
    Shell workbenchShell;
    WidgetFinder finder;

    public AbbotHelper (Shell aShell) {
        finder = WidgetFinderImpl.getDefault();
        workbenchShell = aShell;
    }
    
    // helper method
    protected void clickButton (Shell aShell, String buttonText) throws MultipleFoundException, NotFoundException {
        Button button = (Button) finder.find (aShell, new WidgetTextMatcher (buttonText, Button.class, true));
        ButtonTester.getButtonTester().actionClick (button);        
        waitTime( 1000 );
    }

    // helper method
    protected Shell clickButton (Shell aShell, String buttonText, String newDialogName) throws MultipleFoundException, NotFoundException {
        Button button = (Button) finder.find (aShell, new WidgetTextMatcher (buttonText, Button.class));
        ButtonTester.getButtonTester().actionClick (button);  
        waitTime( 1000 );
        return ShellTester.waitVisible (newDialogName);
    }

    // helper method
    protected Shell clickEnabledButton (Shell aShell, String buttonText, String newDialogName) throws MultipleFoundException, NotFoundException {
        Button button = (Button) finder.find (aShell, new ButtonMultiMatcher (buttonText, Button.class, true));
        ButtonTester.getButtonTester().actionClick (button);  
        waitTime( 1000 );
        return ShellTester.waitVisible (newDialogName);
    }

    // helper method
    protected void doubleClickItem (Shell aShell, String itemText) throws MultipleFoundException, NotFoundException {
        Item item = (Item) finder.find (aShell, new WidgetTextMatcher (itemText, Item.class, true));
        ItemTester.getItemTester().actionDoubleClick(item);       
        waitTime( 1000 );
    }

    // helper method
    protected void clickItem (Shell aShell, String itemText) throws MultipleFoundException, NotFoundException {
        Item item = (Item) finder.find (aShell, new WidgetTextMatcher (itemText, Item.class, true));
        ItemTester.getItemTester().actionClick (item, 3, 3);       
        waitTime( 1000 );
    }

    // helper method    
    protected Shell rightClickItem (Shell aShell, String itemText, String[] menuList, String newDialogName) throws MultipleFoundException, NotFoundException {
        Item item = (Item) finder.find (workbenchShell, new WidgetTextMatcher (itemText, Item.class, true));
        ItemPath anItemPath = new ItemPath (menuList);
        ItemTester.getItemTester().actionClickMenuItem (item, anItemPath);
        waitTime( 1000 );
        return ShellTester.waitVisible (newDialogName);
    }

    // helper method    
    protected void rightClickItem (Shell aShell, String itemText, String[] menuList) throws MultipleFoundException, NotFoundException {
        Item item = (Item) finder.find (workbenchShell, new WidgetTextMatcher (itemText, Item.class, true));
        ItemPath anItemPath = new ItemPath (menuList);
        ItemTester.getItemTester().actionClickMenuItem (item, anItemPath);
        waitTime( 1000 );
    }

    // helper method    
    protected Shell clickMenuItem (Shell aShell, String[] menuList, String newDialogName) throws MultipleFoundException, NotFoundException {
        ItemPath anItemPath = new ItemPath (menuList);
        Menu bar = ShellTester.getShellTester().getMenuBar (aShell);
        MenuTester.getMenuTester().actionClickItem (bar, anItemPath);
        waitTime( 1000 );
        return ShellTester.waitVisible (newDialogName);
    }
    
 // helper method    
    protected void clickMenuItem (Shell aShell, String[] menuList) throws MultipleFoundException, NotFoundException {
        ItemPath anItemPath = new ItemPath (menuList);
        Menu bar = ShellTester.getShellTester().getMenuBar (aShell);
        MenuTester.getMenuTester().actionClickItem (bar, anItemPath);
        waitTime( 1000 );
    }

    // helper method
    protected void clickTreeItem (Shell aShell, String[] treeList) throws MultipleFoundException, NotFoundException {
        ItemPath anItemPath = new ItemPath (treeList);
        Tree tree = (Tree) finder.find (aShell, new WidgetClassMatcher (Tree.class));
        TreeTester.getTreeTester().actionClickItem (tree, anItemPath);
        waitTime( 1000 );
    }

    // helper method
    protected void setTextField (Shell aShell, String oldText, String newText) throws MultipleFoundException, NotFoundException {
        Text text = (Text) finder.find (aShell, new WidgetTextMatcher (oldText, Text.class, true));
        if (oldText.length() > 0)
        {
            TextTester.getTextTester().actionSelect (text, 0, oldText.length());
            TextTester.getTextTester().actionKeyString (newText);
        } else {
            TextTester.getTextTester().actionKeyString (text, newText);  
        }
        waitTime( 1000 );
    }

    // helper method with a leap of faith that the cursor is
    // exactly where we want it to be.
    protected void setCursorText (String newText) {
        TextTester.getTextTester().actionKeyString (newText);
        waitTime( 1000 );
    }
    
    // helper method
    protected void waitForDialogDisposal (Shell aShell) {
        while (!ShellTester.getShellTester().isDisposed (aShell))
            ShellTester.getShellTester().actionDelay (1000);
        // wait an extra 2 seconds
        ShellTester.getShellTester().actionDelay (2000);
    }

    // helper method
    protected void waitForServerStatus (Shell aShell, String itemText, String desiredState) throws MultipleFoundException, NotFoundException {
        // will wait up to 2 minutes for status to change
        boolean statusGood = false;
        int countdown = 120;

        while (statusGood == false && countdown > 0) {
            TreeItem item = (TreeItem) finder.find (aShell, new WidgetTextMatcher (itemText, TreeItem.class));
            if (desiredState.equals (TreeItemTester.getTreeItemTester().getText (item, 1)))
                statusGood = true;
            
            countdown--;
            ShellTester.getShellTester().actionDelay (1000);
        }
        if (statusGood == false)
            throw new NotFoundException ("Unable to start or stop the server");
    }
    
    protected void waitTime (long time) {
        ShellTester.getShellTester().actionDelay (time);
    }

    final class ButtonMultiMatcher extends TextMultiMatcher {
        public ButtonMultiMatcher(String text, Class clazz, boolean mustBeShowing) {
            super(text, clazz, mustBeShowing);
        }

        public ButtonMultiMatcher(String text, boolean mustBeShowing) {
            this(text, Widget.class, mustBeShowing);
        }

        public ButtonMultiMatcher(String text, Class clazz) {
            this(text, clazz, false);
        }

        public ButtonMultiMatcher(String text) {
            this(text, Widget.class, false);
        }

        //TODO need to check to see if more than one are enabled
        public Widget bestMatch(List<Widget> candidates) throws MultipleFoundException {
            Button aButton = null;
            for (int i = 0; i < candidates.size(); i++) {
                aButton = (Button)candidates.get(i);
                    if (ButtonTester.getButtonTester().getEnabled(aButton))
                        return aButton;
            }
            return aButton;
        }
    }
}
