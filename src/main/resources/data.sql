-- Insert users
insert into users (idu) values ('0');
insert into users (idu) values ('1');

-- Insert workspaces
insert into workspace (label, color, emoticon, owner_id) values ('Workspace 1', '#2ecc71', U&'\d83e\dd13', '0');
insert into workspace (label, color, emoticon, owner_id) values ('Workspace 2', '#e74c3c', U&'\d83e\dd14', '0');
insert into workspace (label, color, emoticon, owner_id) values ('Workspace 1', '#8e44ad', U&'\d83e\dd15', '1');
insert into workspace (label, color, emoticon, owner_id) values ('Workspace 2', '#f39c12', U&'\d83e\dd16', '1');

-- share workspace to user
insert into shared_workspaces (workspace_id, user_id) values (3, '0');
insert into shared_workspaces (workspace_id, user_id) values (1, '1');

insert into project (label, color, emoticon) values ('Endor base', '#2980b9', U&'\d83e\dd17');
insert into project (label, color, emoticon) values ('Death star', '#2c3e50', U&'\d83e\dd18');

insert into project_workspace (workspace_id, project_id) values (1, 1);
insert into project_workspace (workspace_id, project_id) values (1, 2);
