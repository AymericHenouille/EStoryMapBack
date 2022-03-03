-- Insert users
insert into users (idu) values ('0');
insert into users (idu) values ('1');

-- Insert workspaces
insert into workspace (label, color, emoticon, owner_id) values ('Workspace 1', '#000000', 'a', '0');
insert into workspace (label, color, emoticon, owner_id) values ('Workspace 2', '#FFFFFF', 'a', '0');
insert into workspace (label, color, emoticon, owner_id) values ('Workspace 1', '#000000', 'a', '1');
insert into workspace (label, color, emoticon, owner_id) values ('Workspace 2', '#FFFFFF', 'a', '1');

-- share workspace to user
insert into shared_workspaces (user_id, workspace_id) values ('0', 3);
insert into shared_workspaces (user_id, workspace_id) values ('1', 1);